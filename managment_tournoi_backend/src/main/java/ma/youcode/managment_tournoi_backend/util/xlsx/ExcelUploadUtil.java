package ma.youcode.managment_tournoi_backend.util.xlsx;

import ma.youcode.managment_tournoi_backend.dto.appUserFileDto.createMemberDto.AppUserRequest;
import ma.youcode.managment_tournoi_backend.exception.ExtensionTypeException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExcelUploadUtil {
    public static void isValidExcelFile(MultipartFile file) {
        if(Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            throw new ExtensionTypeException("Excel file is not supported, format should xls or xlsx");

    }
    public static List<AppUserRequest> readExcelFile(InputStream inputStream) {
        List<AppUserRequest> usersList = new ArrayList<>();
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            usersList = readUsersFromSheet(sheet);

            workbook.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return usersList;
    }

    private static List<AppUserRequest> readUsersFromSheet(Sheet sheet) {
        List<AppUserRequest> usersList = new ArrayList<>();
        int rowIndex = 0;
        for (Row row : sheet) {
            if(rowIndex == 0){
                rowIndex++;
                continue;
            }
            if(!hasNonBlankCell(row)){
                break;
            }
            AppUserRequest user = createUserFromRow(row);
            usersList.add(user);
        }
        return usersList;
    }

    private static AppUserRequest createUserFromRow(Row row) {
        AppUserRequest user = new AppUserRequest();
        for (int i = 0; i < 5; i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            switch (i){
                case 0 -> user.setFirstName(cell.getStringCellValue());
                case 1 -> user.setLastName(cell.getStringCellValue());
                case 2 -> user.setEmail(cell.getStringCellValue());
                case 3 -> user.setClassName(cell.getStringCellValue());
                case 4 -> user.setUrlPicture(cell.getStringCellValue());
                default -> {}
            }
        }
        return user;
    }

    private static boolean hasNonBlankCell(Row row) {
        for (int i = 0; i < 5; i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (cell != null && cell.getCellType()!= CellType.BLANK){
                return true;
            }
        }
        return false;
    }

}

