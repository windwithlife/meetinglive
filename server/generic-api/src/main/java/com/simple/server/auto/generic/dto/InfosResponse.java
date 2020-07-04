package com.simple.server.auto.generic.dto;

import java.io.Serializable;
import java.util.List;

import com.simple.common.api.BaseResponse;

public class InfosResponse extends BaseResponse implements Serializable {
    private static final long  serialVersionUID = 1L;

    private int                itemsCount;

    private List<InfoResponse> items;

    public InfosResponse() {
    }

    public int getItemsCount() {
        return this.itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public List<InfoResponse> getItems() {
        return this.items;
    }

    public void setItems(List<InfoResponse> items) {
        this.items = items;
    }

}
