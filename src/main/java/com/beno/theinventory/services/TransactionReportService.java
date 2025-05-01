package com.beno.theinventory.services;

public interface TransactionReportService {

    public byte[] generateReportFromWarehouse(int sourceWarehouseId);
    public byte[] generateReportFromSupplier(int supplierId);
}
