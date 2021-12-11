package uz.alif.server.controller;// Author - Orifjon Yunusjonov
// t.me/coderr24

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.alif.server.entity.Attachment;
import uz.alif.server.model.Result;
import uz.alif.server.repository.AttachmentRepository;
import uz.alif.server.service.AttachmentService;

import java.net.MalformedURLException;
import java.net.URLEncoder;


@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Value("${upload.folder}")
    private String uploadFolder;

    @GetMapping("/")
    public ResponseEntity getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(attachmentRepository.findAll(PageRequest.of(page, size)));
    }

    @PostMapping("/save")
    public void save(@RequestParam(name = "file") MultipartFile multipartFile){
        attachmentService.save(multipartFile);
    }

    @GetMapping("/preview/{hashId}")
    public ResponseEntity preview(@PathVariable String hashId) throws MalformedURLException {
        Attachment file = attachmentService.findByHashId(hashId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName="+ URLEncoder.encode(file.getName()))
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(new FileUrlResource(String.format("%s/%s",
                        uploadFolder,
                        file.getUploadPath())));
    }

    @GetMapping("/download/{hashId}")
    public ResponseEntity download(@PathVariable String hashId) throws MalformedURLException {
        Attachment file = attachmentService.findByHashId(hashId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName="+ URLEncoder.encode(file.getName()))
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(new FileUrlResource(String.format("%s/%s",
                        uploadFolder,
                        file.getUploadPath())));
    }

    @DeleteMapping("/delete/{hashId}")
    public ResponseEntity delete(@PathVariable String hashId) throws MalformedURLException {
        boolean isDelete = attachmentService.delete(hashId);
        if (!isDelete){
            return new ResponseEntity(new Result(false, "not deleted"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new Result(true,"deleted"));
    }
}
