package com.hassan.careercoachai.service;

import com.hassan.careercoachai.infrastructure.pdf.PdfTextExtractor;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class CvService {

    private final PdfTextExtractor pdfTextExtractor;

    public CvService(PdfTextExtractor pdfTextExtractor) {
        this.pdfTextExtractor = pdfTextExtractor;
    }

    public String extractText(byte[] content) {
        if (content.length == 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Le fichier est vide."
            );
        }

        try {
            String text = pdfTextExtractor.extract(content);

            if (text.isBlank()) {
                throw new ResponseStatusException(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "Aucun texte détecté. Un PDF scanné nécessite un OCR."
                );
            }

            return text;
        } catch (InvalidPasswordException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Le PDF est protégé par un mot de passe.",
                    exception
            );
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    exception.getMessage(),
                    exception
            );
        } catch (IOException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Impossible de lire le PDF : fichier invalide ou endommagé.",
                    exception
            );
        }
    }
}