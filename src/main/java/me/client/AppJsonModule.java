package me.client;

import io.reinert.requestor.core.*;
import io.reinert.requestor.gwtjackson.annotations.*;

// declare this interface to enable auto json serialization
@JsonSerializationModule({
        Todo.class
        // add other classes you want auto generated json serializers
})
public interface AppJsonModule extends SerializationModule {}
