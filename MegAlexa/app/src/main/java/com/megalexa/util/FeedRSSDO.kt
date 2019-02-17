package com.megalexa.util;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.properties.Delegates

@DynamoDBTable(tableName = "megalexa-mobilehub-1331931398-FeedRSS")

class FeedRSSDO {
    private var _iD : Int by Delegates.notNull<Int>()
    private lateinit var _uRL : String
    private lateinit var _typeBlockID : String

    @DynamoDBHashKey(attributeName = "ID")
    @DynamoDBAttribute(attributeName = "ID")
    fun getID() : Int {
        return _iD;
    }

    fun setID( _iD : Int) {
        this._iD = _iD;
    }
    @DynamoDBAttribute(attributeName = "URL")
    fun getURL() : String {
        return _uRL;
    }

    fun setURL( _uRL : String) {
        this._uRL = _uRL;
    }
    @DynamoDBAttribute(attributeName = "typeBlockID")
    fun  getTypeBlockID() : String {
        return _typeBlockID;
    }

    fun setTypeBlockID( _typeBlockID : String) {
        this._typeBlockID = _typeBlockID;
    }

}
