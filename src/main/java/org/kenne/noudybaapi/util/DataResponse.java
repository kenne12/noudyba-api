package org.kenne.noudybaapi.util;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Builder
@Data
public class DataResponse<T> {

    T data;

    public DataResponse(T data) {
        this.data = data;
    }

    public Map<String, T> formatData(String key) {
        Map map = new HashMap();
        map.put(key, data);
        return map;
    }
}
