package com.ob11to.spring.service;

import com.ob11to.spring.dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class XmlUserService {

    private final UserService userService;

    public byte[] generateWorkReport() {
        List<UserReadDto> users = userService.findAll();

        try(var workbook = new XSSFWorkbook();
            var outputStream = new ByteArrayOutputStream()) {

            var sheet = workbook.createSheet("WorkReport");

            //Header
            var header = sheet.createRow(0);
            var listHeader = List.of("Username", "First Name", "Last Name");

            for(int i = 0; i < listHeader.size(); i++) {
                var cell = header.createCell(i);
                cell.setCellValue(listHeader.get(i));
                sheet.autoSizeColumn(i);
            }

            //Row
            for(int i = 0; i < users.size(); i++) {
                var user = users.get(i);
                var row = sheet.createRow(i + 1);

                row.createCell(0)
                        .setCellValue(user.getUsername());
                row.createCell(1)
                        .setCellValue(user.getFirstname());
                row.createCell(2)
                        .setCellValue(user.getLastname());
            }

            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (IOException e) {
            log.error("Ошибка в формировании отчета");
            e.printStackTrace();
            return null;
        }
    }
}
