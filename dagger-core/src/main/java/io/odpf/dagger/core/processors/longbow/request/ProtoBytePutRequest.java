package io.odpf.dagger.core.processors.longbow.request;

import io.odpf.dagger.core.processors.longbow.LongbowSchema;
import io.odpf.dagger.core.processors.longbow.storage.PutRequest;
import io.odpf.dagger.core.sink.ProtoSerializer;
import org.apache.flink.types.Row;

import io.odpf.dagger.core.utils.Constants;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.sql.Timestamp;

/**
 * Create PutRequest in form of proto byte.
 */
public class ProtoBytePutRequest implements PutRequest {
    private static final byte[] COLUMN_FAMILY_NAME = Bytes.toBytes(Constants.LONGBOW_COLUMN_FAMILY_DEFAULT);
    private static final byte[] QUALIFIER_NAME = Bytes.toBytes(Constants.LONGBOW_QUALIFIER_DEFAULT);
    private LongbowSchema longbowSchema;
    private Row input;
    private ProtoSerializer protoSerializer;
    private String tableId;


    /**
     * Instantiates a new Proto byte put request.
     *
     * @param longbowSchema   the longbow schema
     * @param input           the input
     * @param protoSerializer the proto serializer
     * @param tableId         the table id
     */
    public ProtoBytePutRequest(LongbowSchema longbowSchema, Row input, ProtoSerializer protoSerializer, String tableId) {
        this.longbowSchema = longbowSchema;
        this.input = input;
        this.protoSerializer = protoSerializer;
        this.tableId = tableId;
    }

    @Override
    public Put get() {
        Put putRequest = new Put(longbowSchema.getKey(input, 0));
        Timestamp rowtime = (Timestamp) longbowSchema.getValue(input, Constants.ROWTIME);
        putRequest.addColumn(COLUMN_FAMILY_NAME, QUALIFIER_NAME, rowtime.getTime(), protoSerializer.serializeValue(input));
        return putRequest;
    }

    @Override
    public String getTableId() {
        return this.tableId;
    }
}
