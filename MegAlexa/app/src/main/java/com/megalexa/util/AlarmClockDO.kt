package com.megalexa.util;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;


import kotlin.properties.Delegates

@DynamoDBTable(tableName = "megalexa-mobilehub-1331931398-AlarmClock")

class AlarmClockDO {
    private  var _iD : Int by Delegates.notNull<Int>()
    private lateinit var  _date : String
    private lateinit var  _time : String
    private  var _typeBlockID : Int by Delegates.notNull<Int>()

    @DynamoDBHashKey(attributeName = "ID")
    @DynamoDBAttribute(attributeName = "ID")
    fun getID() : Int {
        return _iD
    }

    fun setID( _iD : Int) {
        this._iD = _iD
    }

    @DynamoDBAttribute(attributeName = "date")
    fun  getDate() : String {
        return _date;
    }

    fun setDate( _date : String) {
        this._date = _date;
    }
    @DynamoDBAttribute(attributeName = "time")
    fun  getTime()  : String{
        return _time;
    }

    fun setTime( _time : String) {
        this._time = _time;
    }
    @DynamoDBAttribute(attributeName = "typeBlockID")
    fun getTypeBlockID() : Int {
        return _typeBlockID;
    }

    fun setTypeBlockID( _typeBlockID : Int) {
        this._typeBlockID = _typeBlockID;
    }

}
