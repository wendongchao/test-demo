我也被这个问题困扰了很长时间，无意中看到了poi的处理方式，如下
```
XSSFCellStyle aa = .createCellStyle();
aa.setFillForegroundColor(new XSSFColor(new Color(255,255,255))); 
aa.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
```
我在使用easyExcel的workbook的时候转换了相对应的格式，
处理单元格样式的时候，我分了两种情况，
1. 单个单元格的情况
2. easyExcel表头的情况

先说第一种情况，这里可以设置16色，也可以设置RGB
```
Workbook workbook = writeWorkbookHolder.getWorkbook();
CellStyle cellStyle = initCellStyle(workbook);
XSSFCellStyle xssfCellStyle =(XSSFCellStyle)workbook.createCellStyle();
xssfCellStyle.cloneStyleFrom(cellStyle);
xssfCellStyle.setFillForegroundColor(new XSSFColor(new Color(255,255,255)));
xssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
cell.setCellStyle(xssfCellStyle);
```
注意：最后一定要把这个样式放到cell这个单元格中，

第二种情况，设置easyExcel表头的RGB颜色
首先设置标题头颜色策略，但是不要设置头的部分，只设置内容部分，可以在easyExcel文档中找到“自定义样式”

```
// 内容的策略
WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
WriteFont contentWriteFont = new WriteFont();
contentWriteFont.setFontHeightInPoints((short)11);// 字体大小
contentWriteFont.setFontName("微软雅黑"); // 字体样式
contentWriteCellStyle.setWriteFont(contentWriteFont);
contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);   // 下边框
contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);     // 左边框
contentWriteCellStyle.setBorderTop(BorderStyle.THIN);      // 上边框
contentWriteCellStyle.setBorderRight(BorderStyle.THIN);    // 右边框
// 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
HorizontalCellStyleStrategy horizontalCellStyleStrategy =
        new HorizontalCellStyleStrategy(null, contentWriteCellStyle);
```
使用这个策略我就不说了
自定义拦截器，对head进行单独处理
```
public class CustomHeadWriteHandler extends AbstractCellWriteHandler {

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell,
                                Head head, Integer relativeRowIndex, Boolean isHead) {
        // 设置标题头样式
        if (isHead) {
            Sheet sheet = writeSheetHolder.getSheet();
            Workbook workbook = sheet.getWorkbook();
            CellStyle cellStyle = initCellStyle(workbook);
            XSSFCellStyle xssfCellStyle = (XSSFCellStyle)workbook.createCellStyle();
            xssfCellStyle.cloneStyleFrom(cellStyle);
            xssfCellStyle.setFillForegroundColor(new XSSFColor(new Color(255,255,255)));
            xssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(xssfCellStyle);
        }
    }
}
```

上面的方法
```
private CellStyle initCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);   // 下边框
        style.setBorderLeft(BorderStyle.THIN);     // 左边框
        style.setBorderTop(BorderStyle.THIN);      // 上边框
        style.setBorderRight(BorderStyle.THIN);    // 右边框

        Font font = workbook.createFont();
        font.setFontName("微软雅黑"); // 字体样式
        font.setBold(false);    // 是否加粗
        font.setFontHeightInPoints((short)11);   // 字体大小
        style.setFont(font);
        return style;
    }
```
