package com.megalexa.util;

import android.renderscript.ScriptIntrinsicYuvToRGB
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

@DynamoDBTable(tableName = "megalexa-mobilehub-1331931398-Filter")

class FilterDO {
    private var _iD : Int by Delegates.notNull<Int>()
    private var  _numberOfFiltrable : Int by Delegates.notNull<Int>()
    private var _typeBlockID : Int by Delegates.notNull<Int>()

    @DynamoDBHashKey(attributeName = "ID")
    @DynamoDBAttribute(attributeName = "ID")
    fun getID() : Int{
        return _iD;
    }

    fun setID( _iD : Int) {
        this._iD = _iD;
    }
    @DynamoDBAttribute(attributeName = "numberOfFiltrable")
    fun getNumberOfFiltrable() : Int {
        return _numberOfFiltrable;
    }

    fun setNumberOfFiltrable( _numberOfFiltrable : Int) {
        this._numberOfFiltrable = _numberOfFiltrable;
    }
    @DynamoDBAttribute(attributeName = "typeBlockID")
    fun getTypeBlockID() : Int {
        return _typeBlockID;
    }

    fun setTypeBlockID( _typeBlockID : Int) {
        this._typeBlockID = _typeBlockID;
    }

}
