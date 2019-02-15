package com.megalexa.util;


import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;


@DynamoDBTable(tableName = "megalexa-mobilehub-1331931398-User")

 class UserDO {
    private lateinit var _mail : String;

    @DynamoDBHashKey(attributeName = "Mail")
    @DynamoDBAttribute(attributeName = "Mail")
    fun  getMail() : String {
        return _mail;
    }

    fun setMail(_mail : String) {
        this._mail = _mail;
    }

}
