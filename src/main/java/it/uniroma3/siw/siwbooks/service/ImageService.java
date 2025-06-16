package it.uniroma3.siw.siwbooks.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {
    
    private final String uploadDir = "uploads/images/";
    
    public ImageService() {
        // Crea la directory se non esiste
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Impossibile creare la directory di upload", e);
        }
    }
    
    /**
     * Salva un'immagine singola e restituisce il percorso relativo
     */
    public String saveImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        
        // Genera un nome univoco per il file
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString() + extension;
        
        // Salva il file
        Path filePath = Paths.get(uploadDir + filename);
        Files.copy(file.getInputStream(), filePath);
        
        // Restituisce il percorso relativo per il database
        return "/images/" + filename;
    }
    
    /**
     * Salva multiple immagini e restituisce una lista di percorsi
     */
    public List<String> saveImages(MultipartFile[] files) throws IOException {
        List<String> imagePaths = new ArrayList<>();
        
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String imagePath = saveImage(file);
                if (imagePath != null) {
                    imagePaths.add(imagePath);
                }
            }
        }
        
        return imagePaths;
    }
    
    /**
     * Elimina un'immagine dal filesystem
     */
    public void deleteImage(String imagePath) {
        if (imagePath != null && imagePath.startsWith("/images/")) {
            try {
                String filename = imagePath.substring("/images/".length());
                Path filePath = Paths.get(uploadDir + filename);
                boolean deleted = Files.deleteIfExists(filePath);
                
                if (deleted) {
                    System.out.println("Immagine eliminata con successo: " + imagePath);
                } else {
                    System.out.println("Immagine non trovata (potrebbe essere già stata eliminata): " + imagePath);
                }
            } catch (IOException e) {
                // Log dell'errore ma non interrompere l'operazione
                System.err.println("Errore durante l'eliminazione dell'immagine: " + imagePath + " - " + e.getMessage());
            }
        } else {
            System.out.println("Percorso immagine non valido o non fornito: " + imagePath);
        }
    }
    
    /**
     * Elimina multiple immagini dal filesystem
     */
    public void deleteImages(List<String> imagePaths) {
        if (imagePaths != null && !imagePaths.isEmpty()) {
            for (String imagePath : imagePaths) {
                deleteImage(imagePath);
            }
        }
    }
}
