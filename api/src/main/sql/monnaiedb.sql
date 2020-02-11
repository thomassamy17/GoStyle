drop table user_discount;
drop table user;
drop table discount;

CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `firstname` varchar(45) NOT NULL,
    `name` varchar(45) NOT NULL,
    `password` varchar(45) NOT NULL,
    `email` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `discount` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `rate` varchar(45) NOT NULL,
    `item_name` varchar(45) NOT NULL,
    `code` varchar(45) NOT NULL,
    `utilisation_max` varchar(45) NOT NULL,
    `date_debut_validite` varchar(45) NOT NULL,
    `date_fin_validite` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `user_discount` (
    `user_id` int(11) NOT NULL,
    `discount_id` int(11) NOT NULL,
    `nb_utilisation` int(11) NOT NULL,
    KEY `fk_user` (`user_id`),
    KEY `fk_discount` (`discount_id`),
    CONSTRAINT `fk_discount` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`id`),
    CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);