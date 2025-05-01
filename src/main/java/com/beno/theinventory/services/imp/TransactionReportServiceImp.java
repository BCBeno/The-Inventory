package com.beno.theinventory.services.imp;

import com.beno.theinventory.dtos.ReportDTO;
import com.beno.theinventory.entities.InventoryMovement;
import com.beno.theinventory.entities.Supplier;
import com.beno.theinventory.entities.Warehouse;
import com.beno.theinventory.exceptions.SupplierNotFoundException;
import com.beno.theinventory.exceptions.WarehouseNotFoundException;
import com.beno.theinventory.repositories.InventoryMovementRepository;
import com.beno.theinventory.repositories.SupplierRepository;
import com.beno.theinventory.repositories.WarehouseRepository;
import com.beno.theinventory.services.TransactionReportService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TransactionReportServiceImp implements TransactionReportService {

    private final InventoryMovementRepository inventoryMovementRepository;
    private final WarehouseRepository warehouseRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public byte[] generateReportFromWarehouse(int warehouseId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        try {
            ReportDTO reportDTO = new ReportDTO();
            Warehouse senderWarehouse = warehouseRepository.findById(warehouseId)
                    .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found"));

            reportDTO.setSender(senderWarehouse.getName());

            ArrayList<InventoryMovement> inventoryMovements = inventoryMovementRepository.findAllBySourceWarehouse(senderWarehouse);
            reportDTO.setTransactionNumber(inventoryMovements.size());
            for (InventoryMovement inventoryMovement : inventoryMovements) {
                reportDTO.getReceiverWarehouse().add(inventoryMovement.getDestinationWarehouse().getName());
                reportDTO.getProductName().add(inventoryMovement.getProduct().getName());
                reportDTO.getQuantity().add(inventoryMovement.getQuantity());
                String formattedDate = inventoryMovement.getTimestamp().format(formatter);
                reportDTO.getDate().add(formattedDate);
                reportDTO.getReason().add(inventoryMovement.getReason());
            }

            // Now generate PDF
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Add sender warehouse name
            Paragraph senderInfo = new Paragraph("From: " + reportDTO.getSender(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            senderInfo.setAlignment(Element.ALIGN_LEFT);
            document.add(senderInfo);
            document.add(Chunk.NEWLINE);

            // Create table with headers
            PdfPTable table = new PdfPTable(4); // 4 columns: Product, Quantity, Destination, Date
            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 2, 3, 3});

            // Add table headers
            table.addCell(createHeaderCell("Product"));
            table.addCell(createHeaderCell("Quantity"));
            table.addCell(createHeaderCell("Destination"));
            table.addCell(createHeaderCell("Date"));

            // Add transaction data
            for (int i = 0; i < reportDTO.getTransactionNumber(); i++) {
                table.addCell(new PdfPCell(new Phrase(reportDTO.getProductName().get(i))));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(reportDTO.getQuantity().get(i)))));
                table.addCell(new PdfPCell(new Phrase(reportDTO.getReceiverWarehouse().get(i))));
                table.addCell(new PdfPCell(new Phrase(reportDTO.getDate().get(i))));
            }

            document.add(table);
            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] generateReportFromSupplier(int supplierId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        try {
            ReportDTO reportDTO = new ReportDTO();
            Supplier supplier = supplierRepository.findById(supplierId)
                    .orElseThrow(() -> new SupplierNotFoundException("Supplier not found"));

            reportDTO.setSender(supplier.getName());

            ArrayList<InventoryMovement> inventoryMovements = inventoryMovementRepository.findAllBySupplier(supplier);
            reportDTO.setTransactionNumber(inventoryMovements.size());
            for (InventoryMovement inventoryMovement : inventoryMovements) {
                reportDTO.getReceiverWarehouse().add(inventoryMovement.getDestinationWarehouse().getName());
                reportDTO.getProductName().add(inventoryMovement.getProduct().getName());
                reportDTO.getQuantity().add(inventoryMovement.getQuantity());
                String formattedDate = inventoryMovement.getTimestamp().format(formatter);
                reportDTO.getDate().add(formattedDate);
                reportDTO.getReason().add(inventoryMovement.getReason());
            }

            // Now generate PDF
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Add sender supplier name
            Paragraph senderInfo = new Paragraph("Supplier: " + reportDTO.getSender(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            senderInfo.setAlignment(Element.ALIGN_LEFT);
            document.add(senderInfo);
            document.add(Chunk.NEWLINE);

            // Create table with headers
            PdfPTable table = new PdfPTable(4); // 4 columns: Product, Quantity, Destination, Date
            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 2, 3, 3});

            // Add table headers
            table.addCell(createHeaderCell("Product"));
            table.addCell(createHeaderCell("Quantity"));
            table.addCell(createHeaderCell("Destination"));
            table.addCell(createHeaderCell("Date"));

            // Add transaction data
            for (int i = 0; i < reportDTO.getTransactionNumber(); i++) {
                table.addCell(new PdfPCell(new Phrase(reportDTO.getProductName().get(i)))); // Product Name
                table.addCell(new PdfPCell(new Phrase(String.valueOf(reportDTO.getQuantity().get(i))))); // Quantity
                table.addCell(new PdfPCell(new Phrase(reportDTO.getReceiverWarehouse().get(i)))); // Destination
                table.addCell(new PdfPCell(new Phrase(reportDTO.getDate().get(i)))); // Date
            }

            document.add(table);
            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private PdfPCell createHeaderCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        return cell;
    }
}
