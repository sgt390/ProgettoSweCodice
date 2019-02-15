package com.megalexa.util;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;


import kotlin.properties.Delegates

@DynamoDBTable(tableName = "megalexa-mobilehub-1331931398-Block")

class BlockDO {
    private var _iD : Int by Delegates.notNull<Int>()
    private lateinit var _description : String;
    private lateinit var _name : String

    @DynamoDBHashKey(attributeName = "ID")
    @DynamoDBAttribute(attributeName = "ID")
    fun getID() : Int {
        return _iD;
    }

    fun setID( _iD: Int) {
        this._iD = _iD;
    }
    @DynamoDBAttribute(attributeName = "description")
    fun getDescription() : String {
        return _description;
    }

    fun setDescription( _description : String) {
        this._description = _description;
    }
    @DynamoDBAttribute(attributeName = "name")
    fun getName() : String {
        return _name;
    }

    fun setName( _name : String) {
        this._name = _name;
    }

}
