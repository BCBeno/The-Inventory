package com.beno.theinventory.controllers;


import com.beno.theinventory.services.TransactionReportService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class ReportController {

    private final TransactionReportService transactionReportService;

    @Operation(summary = "Generate a PDF report of transactions from a specific warehouse")
    @GetMapping("/warehouse/{warehouseId}/report")
    public ResponseEntity<byte[]> generateReport(@PathVariable int warehouseId) {
        byte[] pdfData = transactionReportService.generateReportFromWarehouse(warehouseId);
        if (pdfData == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=warehouse_report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfData);
    }

    @Operation(summary = "Generate a PDF report of transactions from a specific supplier")
    @GetMapping("/supplier/{supplierId}/report")
    public ResponseEntity<byte[]> generateReportFromSupplier(@PathVariable int supplierId) {
        byte[] pdfData = transactionReportService.generateReportFromSupplier(supplierId);
        if (pdfData == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=supplier_report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfData);
    }


}
