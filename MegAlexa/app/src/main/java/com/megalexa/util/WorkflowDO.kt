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

@DynamoDBTable(tableName = "megalexa-mobilehub-1331931398-Workflow")

class WorkflowDO {
    private var  _iD : Int by Delegates.notNull<Int>()
    private lateinit var _blocksID : Set<Int>
    private lateinit var _name : String
    private var _userID : Int by Delegates.notNull<Int>()

    @DynamoDBHashKey(attributeName = "ID")
    @DynamoDBAttribute(attributeName = "ID")
    fun getID() : Int {
        return _iD;
    }

    fun setID( _iD : Int) {
        this._iD = _iD;
    }
    @DynamoDBAttribute(attributeName = "blocksID")
    fun getBlocksID() : Set<Int> {
        return _blocksID;
    }

    fun setBlocksID( _blocksID : Set<Int>) {
        this._blocksID = _blocksID;
    }
    @DynamoDBAttribute(attributeName = "name")
    fun getName()  : String{
        return _name;
    }

    fun setName( _name : String) {
        this._name = _name;
    }
    @DynamoDBAttribute(attributeName = "userID")
    fun getUserID() : Int {
        return _userID;
    }

    fun setUserID( _userID : Int) {
        this._userID = _userID;
    }

}