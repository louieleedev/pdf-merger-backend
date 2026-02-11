package com.louilee.pdf_merger_backend.pdf_service.merge;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
public class PdfMergeController {

  private final PdfMergeService pdfMergeService;

  @PostMapping(value = "/merge", consumes = MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<byte[]> mergePdfs(@RequestParam("files") MultipartFile[] files) throws Exception {

    byte[] mergedPdf = pdfMergeService.mergePdfs(files);

    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=merged.pdf")
      .contentType(APPLICATION_PDF)
      .body(mergedPdf);
  }
}
