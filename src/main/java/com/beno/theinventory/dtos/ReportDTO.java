package com.beno.theinventory.dtos;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ReportDTO {
    private String sender;
    private int transactionNumber;
    private ArrayList<String> receiverWarehouse = new ArrayList<>();
    private ArrayList<String> productName = new ArrayList<>();
    private ArrayList<Integer> quantity = new ArrayList<>();
    private ArrayList<String> date  = new ArrayList<>();
    private ArrayList<String> reason = new ArrayList<>();
}
