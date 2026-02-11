package com.louilee.pdf_merger_backend.pdf_service.merge;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfMergeService {

  public byte[] mergePdfs(MultipartFile[] files) throws IOException {

    PDFMergerUtility merger = new PDFMergerUtility();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    for (MultipartFile file : files) {
      merger.addSource(file.getInputStream());
    }

    merger.setDestinationStream(outputStream);
    merger.mergeDocuments(null);

    return outputStream.toByteArray();
  }

}
