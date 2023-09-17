package com.depo.deposervice.util;

import com.depo.deposervice.collection.ContainerizationDocument;
import com.depo.deposervice.entity.Requirement;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class ContainerizationDocumentUtil {

    public static ByteArrayOutputStream formatFilesAndZip(List<ContainerizationDocument> files, List<Requirement> requirements) {
        String plainText;
        for (int i = 0; i < files.size(); i++) {
            plainText = new String(files.get(i).getFile());
            plainText = plainText.replace("{version_info}", requirements.get(i).getVersion());
            files.get(i).setFile(plainText.getBytes());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        StringBuilder outputText = new StringBuilder("version: {containerization_version}\nservices:\n  ");
        files.forEach(f -> {
            try {
                for (byte b : f.getFile()) {
                    if (b == '\n') {
                        outputText.append((char) b);
                        outputText.append("  ");
                    } else {
                        outputText.append((char) b);
                    }

                }
                outputStream.write(outputText.toString().getBytes());
                outputText.setLength(0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    return outputStream;
    }
}
