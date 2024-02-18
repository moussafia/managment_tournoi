package ma.youcode.managment_tournoi_backend.util;

import ma.youcode.managment_tournoi_backend.dto.appUserDto.AppUserRequest;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadUtil {
    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<AppUserRequest> getMembersDataFromExcel(InputStream inputStream) {
        List<AppUserRequest> appUserRequests = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("members");
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                AppUserRequest user = new AppUserRequest();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> user.setFirstName(cell.getStringCellValue());
                        case 1 -> user.setLastName(cell.getStringCellValue());
                        case 2 -> user.setEmail(cell.getStringCellValue());
                        case 3 -> user.setClassName(cell.getStringCellValue());
                        case 4 -> user.setUrlPicture(cell.getStringCellValue());
                        default -> {}
                    }
                    cellIndex++;
                }
                appUserRequests.add(user);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return appUserRequests;
    }
}

