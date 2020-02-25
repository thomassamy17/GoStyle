<?php

require_once 'DbConnect.php';

$response = array();

if(isset($_GET['apicall'])){

    switch($_GET['apicall']){

        case 'login':

            if(paramsValid(array('email', 'password'))){
                $response = login($_POST['email'],md5($_POST['password']),$pdo);
            }
            break;

        case 'promo':
            if(paramsValid(array('code','user_id'))){
                $response = scan($_POST['code'],$_POST['user_id'],$pdo);
            }
            break;

        case 'getPromos':
            if(paramsValid(array('user_id'))){
                $response = getPromosByUser($_POST['user_id'],$pdo);   
            }
            break;

        case 'ajoutUtilisation':
            if(paramsValid(array('user_id','promo_id'))){
                $response = incrementUtilisation($_POST['user_id'],$_POST['promo_id'],$pdo);   
            }
            break;

        default:
            $response['error'] = true;
            $response['message'] = 'Invalid Operation Called';
    }

}else{
    $response['error'] = true;
    $response['message'] = 'Invalid API Call';
}

echo json_encode($response);

function paramsValid($params){

    foreach($params as $param){
        if(!isset($_POST[$param])){
            return false;
        }
    }
    return true;
}

function login($email,$password,$pdo){
    $response = array();
    $sql = $pdo->prepare("SELECT id,email,name,firstname FROM user WHERE email = :email AND password = :password");
    $sql->bindValue(':email', $email, PDO::PARAM_STR);
    $sql->bindValue(':password', $password, PDO::PARAM_STR);
    $sql->execute();
    if ($s = $sql->fetch(PDO::FETCH_OBJ)) {

        $user = array(
            'id'=>$s->id,
            'email'=>$s->email,
            'name'=>$s->name,
            'firstname'=>$s->firstname
        );

        $response['error'] = false;
        $response['message'] = 'Login successfull';
        $response['user'] = $user;
    }else{
        $response['error'] = true;
        $response['message'] = 'Invalid email or password';
    }
    return $response;
}

function scan($code,$user_id,$pdo){
    $response = array();
    $sql = $pdo->prepare("SELECT * FROM discount where discount.code = :code");
    $sql->bindParam(':code', $code);
    $sql->execute();

    if($s=$sql->fetch(PDO::FETCH_OBJ)) {
        $response['error'] = false;
        $sql = $pdo->prepare("INSERT INTO user_discount values (:user_id,:promo_id,:)");
        $sql->bindParam(':user_id', $user_id);
        $sql->bindParam(':promo_id', $s->id);
        $response['message'] = ($sql->execute()) ? 'Code promo ajouté' : 'Vous avez déjà scanner ce code';
        $response['promo'] = array(
            'id'=>$s->id,
            'rate'=>$s->rate,
            'item_name'=>$s->item_name,
            'code'=>$s->code,
            'utilisation_max'=>$s->utilisation_max,
            'date_debut_validite'=>$s->date_debut_validite,
            'date_fin_validite'=>$s->date_fin_validite
        );
    }else{
        $response['error'] = true;
        $response['message'] = 'Invalid discount code';
    }
    return $response;

}

function getPromosByUser($user_id,$pdo){
    $response = array();
    $sql = $pdo->prepare("SELECT * FROM discount d inner join user_discount ud on ud.discount_id = d.id where user_id = :user_id");
    $sql->bindParam(':user_id', $user_id);
    $sql->execute();
    $promos = array();
    $nbPromos = 0;
    while($s=$sql->fetch(PDO::FETCH_OBJ)) {
        $promo = array(
            'id'=>$s->id,
            'rate'=>$s->rate,
            'item_name'=>$s->item_name,
            'code'=>$s->code,
            'utilisation_max'=>$s->utilisation_max,
            'date_fin_validite'=>$s->date_fin_validite,
            'nb_utilisation'=>$s->nb_utilisation
        );
        array_push($promos, $promo);
        $nbPromos++;
    }
    $response['nb_promos'] = $nbPromos;
    $response['promos'] = $promos;
    return $response;
}



function incrementUtilisation($user_id,$promo_id,$pdo){
    $sql = $pdo->prepare("UPDATE user_discount set nb_utilisation = (SELECT nb_utilisation+1 FROM `user_discount` WHERE `user_id`=:user_id and `discount_id`=:promo_id) where user_id = :user_id and discount_id = :promo_id");
    $sql->bindParam(':user_id', $user_id);
    $sql->bindParam(':promo_id', $promo_id);
    $t = $sql->execute();
    return $response['message'] = $t;
}
