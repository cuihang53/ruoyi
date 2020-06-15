package com.ruoyi.service.impl;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.service.ISysSalesOrderService;
import com.ruoyi.system.domain.SysSalesOrder;
import com.ruoyi.system.mapper.SysSalesOrderMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 销售信息录入Service业务层处理
 *
 * @author ruoyi
 * @date 2020-05-16
 */
@Service
public class SysSalesOrderServiceImpl implements ISysSalesOrderService {

    private static final Logger log = LoggerFactory.getLogger(SysSalesOrderServiceImpl.class);
    @Autowired
    private SysSalesOrderMapper sysSalesOrderMapper;


    /**
     * 查询销售信息录入
     *
     * @param salesDetailId 销售信息录入ID
     * @return 销售信息录入
     */
    @Override
    public SysSalesOrder selectSysSalesDetailById(Long salesDetailId) {
        return sysSalesOrderMapper.selectSysSalesDetailById(salesDetailId);
    }

    /**
     * 查询销售信息录入列表
     *
     * @param sysSalesDetail 销售信息录入
     * @return 销售信息录入
     */
    @Override
    public List<SysSalesOrder> selectSysSalesDetailList(SysSalesOrder sysSalesDetail) {
        return sysSalesOrderMapper.selectSysSalesDetailList(sysSalesDetail);
    }

    /**
     * 选择性导出
     *
     * @param checkedNum
     */
    @Override
    public AjaxResult importExcel(String checkedNum, SysSalesOrder salesOrder, HttpServletRequest request, HttpServletResponse response) {
        Workbook wb = null;
        OutputStream out = null;
        try {
            List<SysSalesOrder> list = sysSalesOrderMapper.selectSysSalesDetailList(salesOrder);
            double area = 0;
            double innerarea = 0;
            double realarea = 0;
            double bookBuyerPrice = 0;
            double bookBuyerAllprice = 0;
            double contractPrice = 0;
            double contractAllprice = 0;
            double installmentPrice = 0;
            double mortgagePrice = 0;
            double rantPrice = 0;
            double rdInstallmentPrice = 0;
            double rmiInstallmentPrice = 0;
            double collection = 0;
            double surplusPrice = 0;

            wb = new SXSSFWorkbook(500);
            //创建表格样式
            Map<String, CellStyle> styles = createStyles(wb);
            Sheet sheet = wb.createSheet();
            int rowNum = 0;
            //创建表头
            Row row = sheet.createRow(rowNum);
            rowNum++;
            int cellNum = 0;
            for (String str : checkedNum.split(",")) {
                Cell cell = row.createCell(cellNum);
                cell.setCellStyle(styles.get("header"));
                sheet.setColumnWidth(cellNum, (str.length() + 3) * 600);
                cell.setCellValue(checkedNum.split(",")[cellNum]);
                cellNum++;
            }
            for (SysSalesOrder order : list) {
                row = sheet.createRow(rowNum);
                cellNum = 0;
                for (String str : checkedNum.split(",")) {
                    Cell cell = row.createCell(cellNum);
                    cell.setCellStyle(styles.get("data"));
                    if (str.equals("所属楼盘")) {
                        cell.setCellValue(StringUtils.isEmpty(order.getBulding().getBuildingName()) ? "" : order.getBulding().getBuildingName());
                    } else if (str.equals("所属单元")) {
                        cell.setCellValue(StringUtils.isEmpty(order.getHouseUnit().getHouseUnitName()) ? "" : order.getHouseUnit().getHouseUnitName());
                    } else if (str.equals("关联房号")) {
                        cell.setCellValue(StringUtils.isEmpty(order.getHouse().getHouseNumber()) ? "" : order.getHouse().getHouseNumber());
                    } else if (str.equals("建筑面积")) {
                        cell.setCellValue(order.getHouse().getArea());
                        area += order.getHouse().getArea();
                    } else if (str.equals("套内面积")) {
                        cell.setCellValue(order.getHouse().getInnerArea());
                        innerarea += order.getHouse().getInnerArea();
                    } else if (str.equals("实测面积")) {
                        cell.setCellValue(order.getHouse().getRealArea());
                        realarea += order.getHouse().getRealArea();
                    } else if (str.equals("类型")) {
                        cell.setCellValue(StringUtils.isEmpty(order.getHouse().getBuildingType()) ? "" : order.getHouse().getBuildingType());
                    } else if (str.equals("销售底价")) {
                        cell.setCellValue(order.getSalseFloorPrice());
                    } else if (str.equals("销售单状态")) {
                        cell.setCellValue(StringUtils.isEmpty(order.getOrderStatus()) ? "" : order.getOrderStatus());
                    } else if (str.equals("认购人")) {
                        cell.setCellValue(StringUtils.isEmpty(order.getBookBuyer()) ? "" : order.getBookBuyer());
                    } else if (str.equals("认购时间")) {
                        cell.setCellValue(order.getBuyertime() == null ? "" : DateUtils.parseDateToStr("YYYY-MM-DD", order.getBuyertime()));
                    } else if (str.equals("认购单价")) {
                        cell.setCellValue(order.getBookBuyerPrice());
                        bookBuyerPrice += Double.parseDouble(order.getBookBuyerPrice());
                    } else if (str.equals("认购总价")) {
                        cell.setCellValue(order.getBookBuyerAllprice());
                        bookBuyerAllprice += Double.parseDouble(order.getBookBuyerAllprice());
                    } else if (str.equals("认购人联系方式")) {
                        cell.setCellValue(StringUtils.isEmpty(order.getBookBuyerPhone()) ? "" : order.getBookBuyerPhone());
                    } else if (str.equals("签约人")) {
                        cell.setCellValue(StringUtils.isEmpty(order.getFinalBuyer()) ? "" : order.getFinalBuyer());
                    } else if (str.equals("签合同时间")) {
                        cell.setCellValue(order.getContracttime() == null ? "" : DateUtils.parseDateToStr("YYYY-MM-DD", order.getContracttime()));
                    } else if (str.equals("签约人联系方式")) {
                        cell.setCellValue(StringUtils.isEmpty(order.getFinalBuyerPhone()) ? "" : order.getFinalBuyerPhone());
                    } else if (str.equals("付款方式")) {
                        cell.setCellValue(StringUtils.isEmpty(order.getPayMethod()) ? "" : order.getPayMethod());
                    } else if (str.equals("合同单价")) {
                        cell.setCellValue(order.getContractPrice());
                        contractPrice += Double.parseDouble(order.getContractPrice());
                    } else if (str.equals("合同总价")) {
                        cell.setCellValue(order.getContractAllprice());
                        contractAllprice += Double.parseDouble(order.getContractAllprice());
                    } else if (str.equals("首付金额")) {
                        cell.setCellValue(order.getInstallmentPrice());
                        installmentPrice += Double.parseDouble(order.getInstallmentPrice());
                    } else if (str.equals("按揭金额")) {
                        cell.setCellValue(order.getMortgagePrice());
                        mortgagePrice += Double.parseDouble(order.getMortgagePrice());
                    } else if (str.equals("返租金额")) {
                        cell.setCellValue(order.getRantPrice());
                        rantPrice += Double.parseDouble(order.getRantPrice());
                    } else if (str.equals("已收首付金额")) {
                        cell.setCellValue(order.getRdInstallmentPrice());
                        rdInstallmentPrice += Double.parseDouble(order.getRdInstallmentPrice());
                    } else if (str.equals("剩余首付金额")) {
                        cell.setCellValue(order.getRmiInstallmentPrice());
                        rmiInstallmentPrice += Double.parseDouble(order.getRmiInstallmentPrice());
                    } else if (str.equals("已收款金额")) {
                        cell.setCellValue(order.getCollection());
                        collection += Double.parseDouble(order.getCollection());
                    } else if (str.equals("剩余金额")) {
                        cell.setCellValue(order.getSurplusPrice());
                        surplusPrice += Double.parseDouble(order.getSurplusPrice());
                    } else if (str.equals("优惠说明")) {
                        cell.setCellValue(StringUtils.isEmpty(order.getRemark()) ? "" : order.getRemark());
                    }
                    cellNum++;
                }
                rowNum++;
            }

            row = sheet.createRow(rowNum);
            cellNum = 0;
            for (String str : checkedNum.split(",")) {
                Cell cell = row.createCell(cellNum);
                cell.setCellStyle(styles.get("lastRow"));

                if (str.equals("所属楼盘")) {
                    cell.setCellValue("");
                } else if (str.equals("所属单元")) {
                    cell.setCellValue("");
                } else if (str.equals("关联房号")) {
                    cell.setCellValue("");
                } else if (str.equals("建筑面积")) {
                    cell.setCellValue(area);
                } else if (str.equals("套内面积")) {
                    cell.setCellValue(innerarea);
                } else if (str.equals("实测面积")) {
                    cell.setCellValue(realarea);
                } else if (str.equals("类型")) {
                    cell.setCellValue("");
                } else if (str.equals("销售底价")) {
                    cell.setCellValue("");
                } else if (str.equals("销售单状态")) {
                    cell.setCellValue("");
                } else if (str.equals("认购人")) {
                    cell.setCellValue("");
                } else if (str.equals("认购时间")) {
                    cell.setCellValue("");
                } else if (str.equals("认购单价")) {
                    cell.setCellValue("认购平均单价："+(bookBuyerPrice/list.size())*100/100);
                } else if (str.equals("认购总价")) {
                    cell.setCellValue(bookBuyerAllprice);
                } else if (str.equals("认购人联系方式")) {
                    cell.setCellValue("");
                } else if (str.equals("签约人")) {
                    cell.setCellValue("");
                } else if (str.equals("签合同时间")) {
                    cell.setCellValue("");
                } else if (str.equals("签约人联系方式")) {
                    cell.setCellValue("");
                } else if (str.equals("付款方式")) {
                    cell.setCellValue("");
                } else if (str.equals("合同单价")) {
                    cell.setCellValue("合同平均单价："+(contractPrice/list.size())*100/100);
                } else if (str.equals("合同总价")) {
                    cell.setCellValue(contractAllprice);
                } else if (str.equals("首付金额")) {
                    cell.setCellValue(installmentPrice);
                } else if (str.equals("按揭金额")) {
                    cell.setCellValue(mortgagePrice);
                } else if (str.equals("返租金额")) {
                    cell.setCellValue(rantPrice);
                } else if (str.equals("已收首付金额")) {
                    cell.setCellValue(rdInstallmentPrice);
                } else if (str.equals("剩余首付金额")) {
                    cell.setCellValue(rmiInstallmentPrice);
                } else if (str.equals("已收款金额")) {
                    cell.setCellValue(collection);
                } else if (str.equals("剩余金额")) {
                    cell.setCellValue(surplusPrice);
                } else if (str.equals("优惠说明")) {
                    cell.setCellValue("");
                }

                cellNum++;
            }

            String fileName = UUID.randomUUID().toString() + ".xlsx";
            String filePath = getAbsoluteFile(fileName);
            out = new FileOutputStream(filePath);
            wb.write(out);
            return AjaxResult.success(fileName);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            throw new BusinessException("导出Excel失败，请联系网站管理员！");
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }



    /**
     * 创建表格样式
     *
     * @param wb 工作薄对象
     * @return 样式列表
     */
    private Map<String, CellStyle> createStyles(Workbook wb) {
        // 写入各条记录,每条记录对应excel表中的一行
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);


        style = wb.createCellStyle();
        dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        dataFont.setBold(true);
        style.setFont(dataFont);
        styles.put("lastRow", style);

        return styles;
    }


    public String getAbsoluteFile(String filename) {
        String downloadPath = Global.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }

    /**
     * 新增销售信息录入
     *
     * @param sysSalesDetail 销售信息录入
     * @return 结果
     */
    @Override
    public int insertSysSalesDetail(SysSalesOrder sysSalesDetail) {
        return sysSalesOrderMapper.insertSysSalesDetail(sysSalesDetail);
    }

    /**
     * 修改销售信息录入
     *
     * @param sysSalesDetail 销售信息录入
     * @return 结果
     */
    @Override
    public int updateSysSalesDetail(SysSalesOrder sysSalesDetail) {
        sysSalesDetail.setUpdateTime(DateUtils.getNowDate());
        return sysSalesOrderMapper.updateSysSalesDetail(sysSalesDetail);
    }

    /**
     * 删除销售信息录入对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysSalesDetailByIds(String ids) {
        return sysSalesOrderMapper.deleteSysSalesDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除销售信息录入信息
     *
     * @param salesDetailId 销售信息录入ID
     * @return 结果
     */
    @Override
    public int deleteSysSalesDetailById(Long salesDetailId) {
        return sysSalesOrderMapper.deleteSysSalesDetailById(salesDetailId);
    }
}
