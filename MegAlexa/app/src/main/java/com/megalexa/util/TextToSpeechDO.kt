package com.megalexa.util;

import android.renderscript.ScriptIntrinsicYuvToRGB
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.amazonaws.services.kms.model.DescribeKeyRequest

import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.properties.Delegates

@DynamoDBTable(tableName = "megalexa-mobilehub-1331931398-TextToSpeech")

public class TextToSpeechDO {
    private var _iD : Int by Delegates.notNull<Int>();
    private lateinit var _text : String
    private var _typeBlockID : Int by Delegates.notNull<Int>()

    @DynamoDBHashKey(attributeName = "ID")
    @DynamoDBAttribute(attributeName = "ID")
    fun getID()  : Int{
        return _iD;
    }

    fun setID( _iD : Int) {
        this._iD = _iD;
    }
    @DynamoDBAttribute(attributeName = "text")
    fun getText() : String {
        return _text;
    }

    fun setText( _text : String) {
        this._text = _text;
    }
    @DynamoDBAttribute(attributeName = "typeBlockID")
    fun getTypeBlockID() : Int {
        return _typeBlockID;
    }

    fun setTypeBlockID( _typeBlockID : Int) {
        this._typeBlockID = _typeBlockID;
    }

}
