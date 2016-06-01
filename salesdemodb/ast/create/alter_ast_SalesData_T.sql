

ALTER TABLE `ast_SalesData_T` ADD CONSTRAINT FOREIGN KEY (`reatilercode`) REFERENCES `ast_Retailer_M`(`retailercode`);



ALTER TABLE `ast_SalesData_T` ADD CONSTRAINT FOREIGN KEY (`materialcode`) REFERENCES `ast_Material_M`(`materialcode`);



ALTER TABLE `ast_SalesData_T` ADD CONSTRAINT FOREIGN KEY (`categoryId`) REFERENCES `ast_Category_M`(`categoryId`);



ALTER TABLE `ast_SalesData_T` ADD CONSTRAINT FOREIGN KEY (`channelId`) REFERENCES `ast_Channel_M`(`channelId`);



ALTER TABLE `ast_SalesData_T` ADD CONSTRAINT FOREIGN KEY (`brandcode`) REFERENCES `ast_Brand_M`(`brandcode`);

