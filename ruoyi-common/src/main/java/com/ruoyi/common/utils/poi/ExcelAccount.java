package com.ruoyi.common.utils.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class ExcelAccount {

    /**
     * 导出excel表格方法一
     * @param response
     * @param data
     */
    public void exportExcel(HttpServletResponse response, String data) {

        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("前台用户表数据");
            HSSFRow row = sheet.createRow(0);
            // Sheet样式
            HSSFCellStyle style = wb.createCellStyle();//设置标题样式
            String[] splitData = data.split(",");


            Font font = wb.createFont();
            font.setFontHeightInPoints((short) 10);
            font.setColor(HSSFColor.DARK_TEAL.index);
            style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
         //   style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
         //   style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
            style.setFont(font);
            sheet.autoSizeColumn(1, true);
            row.setHeightInPoints(26);


            HSSFCellStyle centerstyle = wb.createCellStyle();//设置普通表格样式
          //  centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体

            String fileName = "前台用户数据.xls";
            setResponseHeader(response, fileName);
            if (splitData != null && splitData.length > 0) {
                int i = 0;
                for (String str : splitData) {
                    HSSFCell cell = row.createCell(i);
                    if ("loginName".equals(str)) {
                        cell.setCellValue("用户登录名");

                    } else if ("companyCnName".equals(str)) {
                        cell.setCellValue("企业名称(中文)");
                        cell.setCellStyle(style);
                    } else if ("companyEnName".equals(str)) {
                        cell.setCellValue("企业名称(英文)");

                    } else if ("companyCnAddress".equals(str)) {
                        cell.setCellValue("企业地址(中文)");

                    } else if ("cnZhuYingProd".equals(str)) {
                        cell.setCellValue("主营产品(中文)");

                    } else if ("companyPhone".equals(str)) {
                        cell.setCellValue("公司电话");

                    } else if ("fzrName".equals(str)) {
                        cell.setCellValue("外贸负责人姓名");

                    } else if ("fzrSex".equals(str)) {
                        cell.setCellValue("外贸负责人性别");

                    } else if ("fzrPosition".equals(str)) {
                        cell.setCellValue("外贸负责人职位");

                    } else if ("fzrMobile".equals(str)) {
                        cell.setCellValue("外贸负责人手机");

                    } else if ("fzrEmail".equals(str)) {
                        cell.setCellValue("外贸负责人邮箱");

                    } else if ("fzrQq".equals(str)) {
                        cell.setCellValue("外贸负责人QQ");

                    } else if ("name".equals(str)) {
                        cell.setCellValue("姓名");

                    } else if ("sex".equals(str)) {
                        cell.setCellValue("性别");

                    } else if ("mobile".equals(str)) {
                        cell.setCellValue("手机号码");

                    } else if ("email".equals(str)) {
                        cell.setCellValue("邮箱");

                    } else if ("emailStatus".equals(str)) {
                        cell.setCellValue("邮箱状态");

                    } else if ("birthday".equals(str)) {
                        cell.setCellValue("生日");

                    } else if ("accountType".equals(str)) {
                        cell.setCellValue("用户类型");

                    } else if ("address".equals(str)) {
                        cell.setCellValue("个人地址");

                    } else if ("qq".equals(str)) {
                        cell.setCellValue("个人qq");

                    } else if ("companyUrl".equals(str)) {
                        cell.setCellValue("公司网站");

                    } else if ("registerType".equals(str)) {
                        cell.setCellValue("注册类型");

                    } else if ("shiBie".equals(str)) {
                        cell.setCellValue("识别码");

                    } else if ("tuiJianId".equals(str)) {
                        cell.setCellValue("推荐id");

                    }
                    cell.setCellStyle(style);
                    i++;
                }
            }
      /**      List<Account> findAccountList = accountService.findAccountList(account);
            if (findAccountList != null && findAccountList.size() > 0) {
                int m = 1;
                for (Account at : findAccountList) {
                    row = sheet.createRow(m);

                    int n = 0;
                    for (String str : splitData) {
                        String cellVal = "";
                        if ("loginName".equals(str)) {
                            cellVal = at.getLoginName();
                        } else if ("companyCnName".equals(str)) {
                            cellVal = at.getCompanyCnName();
                        } else if ("companyEnName".equals(str)) {
                            cellVal = at.getCompanyEnName();
                        } else if ("companyCnAddress".equals(str)) {
                            cellVal = at.getCompanyCnAddress();
                        } else if ("cnZhuYingProd".equals(str)) {
                            cellVal = at.getCnZhuYingProd();
                        } else if ("companyPhone".equals(str)) {
                            cellVal = at.getCompanyPhone();
                        } else if ("fzrName".equals(str)) {
                            cellVal = at.getFzrName();
                        } else if ("fzrSex".equals(str)) {
                            if (at.getFzrSex() != null) {
                                if ("1".equals(at.getFzrSex())) {
                                    cellVal = "男";
                                } else if ("2".equals(at.getFzrSex())) {
                                    cellVal = "女";
                                }
                            } else {
                                cellVal = "";
                            }

                        } else if ("fzrPosition".equals(str)) {
                            cellVal = at.getFzrPosition();
                        } else if ("fzrMobile".equals(str)) {
                            cellVal = at.getFzrMobile();
                        } else if ("fzrEmail".equals(str)) {
                            cellVal = at.getFzrEmail();
                        } else if ("fzrQq".equals(str)) {
                            cellVal = at.getFzrQq();
                        } else if ("name".equals(str)) {
                            cellVal = at.getName();
                        } else if ("sex".equals(str)) {
                            if (at.getSex() != null) {
                                if ("1".equals(at.getSex())) {
                                    cellVal = "男";
                                } else if ("2".equals(at.getSex())) {
                                    cellVal = "女";
                                }
                            } else {
                                cellVal = "";
                            }

                        } else if ("mobile".equals(str)) {
                            cellVal = at.getMobile();
                        } else if ("email".equals(str)) {
                            cellVal = at.getEmail();
                        } else if ("emailStatus".equals(str)) {

                            if (at.getEmailStatus() != null) {
                                if (at.getEmailStatus() == 0) {
                                    cellVal = "未验证";
                                } else if (at.getEmailStatus() == 1) {
                                    cellVal = "已验证";
                                } else {
                                    cellVal = "";
                                }
                            } else {
                                cellVal = "";
                            }

                        } else if ("birthday".equals(str)) {
                            if (at.getBirthday() != null) {
                                cellVal = DateUtils.formatDate(at.getBirthday(), "yyyy-MM-dd");
                            } else {
                                cellVal = null;
                            }

                        } else if ("accountType".equals(str)) {
                            if (at.getAccountType() != null) {
                                if (at.getAccountType() == 1) {
                                    cellVal = "企业用户";
                                } else if (at.getAccountType() == 2) {
                                    cellVal = "个人用户";
                                } else {
                                    cellVal = "";
                                }
                            } else {
                                cellVal = "";
                            }

                        } else if ("address".equals(str)) {
                            cellVal = at.getAddress();
                        } else if ("qq".equals(str)) {
                            cellVal = at.getQq();
                        } else if ("companyUrl".equals(str)) {
                            cellVal = at.getCompanyUrl();
                        } else if ("registerType".equals(str)) {
                            cellVal = at.getRegisterType();
                        } else if ("shiBie".equals(str)) {
                            cellVal = at.getShiBie();
                        } else if ("tuiJianId".equals(str)) {
                            cellVal = String.valueOf(at.getTuiJianId());
                        }
                        //row.createCell(n).setCellStyle(centerstyle);
                        HSSFCell createCell = row.createCell(n);
                        createCell.setCellValue(cellVal);
                        createCell.setCellStyle(centerstyle);
                        //row.createCell(n).setCellValue(cellVal);

                        n++;
                    }
                    m++;
                }
            }
       **/
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
