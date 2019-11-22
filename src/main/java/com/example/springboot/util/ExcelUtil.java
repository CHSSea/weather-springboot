package com.example.springboot.util;

import com.example.springboot.bean.Weather;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: springboot
 * @description: 表格导出的工具类
 * @author: Haisheng
 * @create: 2019-11-21 15:00
 **/
public class ExcelUtil {

    /**
     *
     * @param response
     * @param excelDate  excel数据
     * @param sheetName  表格名
     * @param fileName   文件名
     * @param columnWidth 表格列的宽度
     */
    public static void exportExcel(HttpServletResponse response, List<List<String>> excelDate,String sheetName,
                                   String fileName,int columnWidth) throws IOException {
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //声明一个表格，设置表格的名字
        HSSFSheet sheet = workbook.createSheet(sheetName);

        //设置表格列宽度
        sheet.setDefaultColumnWidth(columnWidth);

        //写入List中的数据
        int rowIndex = 0;
        for(List<String> date : excelDate){
            //创建一个row行，然后自增1
            HSSFRow row = sheet.createRow(rowIndex++);
            //便利添加数据
            for(int i = 0; i < date.size(); i++){
                //创建一个单元格
                HSSFCell cell = row.createCell(i);

                //创建一个内容对象
                HSSFRichTextString textString = new HSSFRichTextString(date.get(i));

                //将内容写入单元格
                cell.setCellValue(textString);
            }
        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //设置导出Excel的名称
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
        workbook.write(response.getOutputStream());

        //关闭workbook
        workbook.close();
    }

    public static void exportExcelWithObject(HttpServletResponse response, List<Weather> excelDate, String sheetName,
                                             String fileName, int columnWidth) throws IOException {
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //声明一个表格，设置表格的名字
        HSSFSheet sheet = workbook.createSheet(sheetName);

        //设置表格列宽度
        sheet.setDefaultColumnWidth(columnWidth);

        HSSFRow row = null;
        //设置表格题目
        row  = sheet.createRow(0);
        row.createCell(0).setCellValue("天气信息表");

        //设置表格列名
        row = sheet.createRow(1);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("City");
        row.createCell(2).setCellValue("Time");
        row.createCell(3).setCellValue("Dn");
        row.createCell(4).setCellValue("Detail");
        row.createCell(5).setCellValue("Temperature");
        row.createCell(6).setCellValue("Wind");
        row.createCell(7).setCellValue("WindSize");
        row.createCell(8).setCellValue("UnKnow");
        row.createCell(9).setCellValue("CreateTime");

        int rowIndex = 2;
        Weather weather = null;
        for(int i = 0; i < excelDate.size(); i++){
            //创建一个row行，然后自增1
            row = sheet.createRow(rowIndex++);
            //获取对象的值
            weather = excelDate.get(i);
            row.createCell(0).setCellValue(weather.getId());
            row.createCell(1).setCellValue(weather.getCity());
            row.createCell(2).setCellValue(weather.getTime());
            row.createCell(3).setCellValue(weather.getDn());
            row.createCell(4).setCellValue(weather.getDetail());
            row.createCell(5).setCellValue(weather.getTemperature());
            row.createCell(6).setCellValue(weather.getWind());
            row.createCell(7).setCellValue(weather.getWindSize());
            row.createCell(8).setCellValue(weather.getUnknow());
            row.createCell(9).setCellValue(DateUtil.date2String(weather.getCreateTime()));
        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //设置导出Excel的名称
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
        workbook.write(response.getOutputStream());

        //关闭workbook
        workbook.close();
    }
}
