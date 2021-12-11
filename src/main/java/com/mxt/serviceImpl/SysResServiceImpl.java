package com.mxt.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.mxt.bean.SysRes;
import com.mxt.bean.SysRole;
import com.mxt.core.util.StringUtils;
import com.mxt.core.util.view.ZtreeView;
import com.mxt.dao.SysResMapper;
import com.mxt.dao.SysRoleMapper;
import com.mxt.dao.SysRoleResMapper;
import com.mxt.service.SysResService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("sysResService")
public class SysResServiceImpl extends BaseServiceImpl<SysRes,SysResMapper> implements SysResService {
    @Resource
    private SysResMapper sysResMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRoleResMapper sysRoleResMapper;
    /**
     * ��ȡztree����
     *
     * @return
     */
    @Override
    public List<ZtreeView> getZtreeViewList() {
        {
            List<ZtreeView> ztreeViews = new ArrayList<ZtreeView>();
            List<SysRes> sysResType = this.getTopList();
            if (sysResType.size() > 0) {
                int i = 1;
                for (SysRes sysRes : sysResType) {
                    ztreeViews.add(new ZtreeView(sysRes.getId(), sysRes.getPid(), sysRes.getName(),
                            i == 1 ? true : false));
                }
                i++;
            }
            return ztreeViews;
        }
    }
    
    /**
     * ��ȡ������
     *
     * @return
     */
    public List<SysRes> getTopList() {
        return sysResMapper.selectAll().stream().filter(sysRes -> sysRes.getType().equals(1)).collect(Collectors.toList());
    }
    
    /**
     * �������ý���״̬
     *
     * @param resId
     * @param status
     * @return
     */
    @Override
    public Integer setEnabled(Integer resId, Integer status) {
        SysRes sysRes = sysResMapper.selectByPrimaryKey(resId);
        if (sysRes == null) {
            return -1;
        } else {
            sysRes.setEnabled(status);
            sysResMapper.updateByPrimaryKey(sysRes);
            return 0;
        }
    }
    
    /**
     * ɾ��
     *
     * @param id
     * @return
     */
    @Override
    public void deleteRes(Integer id) {
        Integer flag = sysResMapper.deleteByPrimaryKey(id);
        if (flag > 0) {
        } else {
        }
    }
    
    /**
     * ����resIdɾ��Ȩ�޽�ɫ��Դ������
     *
     * @param id
     * @return
     */
    @Override
    public void deleteByResId(Integer id) {
        sysRoleResMapper.deleteByResId(id);
    }
    
    /**
     * ��ȡ��Դ�б����Խṹ
     *
     * @return
     */
    @Override
    public String getTreeGridView() {
        {
            Map<String, Object> rows = new HashMap<>();
            List<Map<String, Object>> list = new ArrayList<>();
            List<SysRes> allTopMenuList = sysResMapper.selectAll();
            
            allTopMenuList.stream()
                    .filter(sysRes -> sysRes.getPid() == null)
                    .forEach(item -> {
                        Map<String, Object> mapItem = new HashMap<>();
                        mapItem.put("id", item.getId());
                        mapItem.put("name", item.getName());
                        mapItem.put("pid", item.getPid());
                        mapItem.put("des", item.getDes());
                        mapItem.put("url", item.getUrl());
                        mapItem.put("loaded", "true");
                        mapItem.put("expanded", "true");
                        mapItem.put("seq", item.getSeq());
                        mapItem.put("isLeaf", item.getType() == 1 ? "false" : "true");
                        mapItem.put("level", 0);
                        mapItem.put("type", item.getType());
                        mapItem.put("enabled", item.getEnabled());
                        
                        long count = allTopMenuList.stream().filter(sysRes -> sysRes.getPid() != null && sysRes.getPid().equals(item.getId())).count();
                        if (count == 0) {
                            mapItem.put("isLeaf", "true");
                        } else {
                            mapItem.put("isLeaf", "false");
                        }
                        
                        list.add(mapItem);
                        allTopMenuList
                                .stream()
                                .filter(sysRes -> sysRes.getPid() != null && sysRes.getPid().equals(item.getId()))
                                .forEach(subItem -> {
                                    Map<String, Object> mapItemSub = new HashMap<>();
                                    mapItemSub.put("id", subItem.getId());
                                    mapItemSub.put("name", subItem.getName());
                                    mapItemSub.put("pid", subItem.getPid());
                                    mapItemSub.put("des", subItem.getDes());
                                    mapItemSub.put("url", subItem.getUrl());
                                    mapItemSub.put("loaded", "true");
                                    mapItemSub.put("expanded", "true");
                                    mapItemSub.put("seq", subItem.getSeq());
                                    mapItemSub.put("type", subItem.getType());
                                    mapItemSub.put("level", 1);
                                    mapItemSub.put("enabled", subItem.getEnabled());
                                    
                                    long countSub = allTopMenuList.stream().filter(sysRes -> sysRes.getPid() != null && sysRes.getPid().equals(subItem.getId())).count();
                                    
                                    if (countSub == 0) {
                                        mapItemSub.put("isLeaf", "true");
                                    } else {
                                        mapItemSub.put("isLeaf", "false");
                                    }
                                    
                                    list.add(mapItemSub);
                                    
                                    allTopMenuList
                                            .stream()
                                            .filter(itemLast -> itemLast.getPid() != null && itemLast.getPid().equals(subItem.getId()))
                                            .forEach(itemLast -> {
                                                Map<String, Object> mapItemLast = new HashMap<>();
                                                mapItemLast.put("id", itemLast.getId());
                                                mapItemLast.put("name", itemLast.getName());
                                                mapItemLast.put("pid", itemLast.getPid());
                                                mapItemLast.put("des", itemLast.getDes());
                                                mapItemLast.put("url", itemLast.getUrl());
                                                mapItemLast.put("loaded", "true");
                                                mapItemLast.put("expanded", "true");
                                                mapItemLast.put("seq", itemLast.getSeq());
                                                mapItemLast.put("isLeaf", "true");
                                                mapItemLast.put("type", itemLast.getType());
                                                mapItemLast.put("level", 2);
                                                mapItemLast.put("enabled", itemLast.getEnabled());
                                                list.add(mapItemLast);
                                            });
                                    
                                });
                    });
            rows.put("rows", list);
            return JSON.toJSONString(rows);
        }
    }
    
    /**
     * �����û�id��url�������˵�
     *
     * @param uid
     * @param url
     * @return
     */
    @Override
    public String getSysUserMenuView(Integer uid, String url) {
        StringBuilder sbBuilder = new StringBuilder("<ul class=\"nav nav-list\">");
        List<SysRes> allMenuList = getSysResList(uid, 1);
        Map<Integer, List<SysRes>> subMapList = new HashMap<Integer, List<SysRes>>();
        for (SysRes menu : allMenuList) {
            if (menu.getPid() != null) {
                List<SysRes> list = subMapList.get(menu.getPid());
                if (list == null) {
                    list = new ArrayList<SysRes>();
                }
                list.add(menu);
                subMapList.put(menu.getPid(), list);
            }
        }
        SysRes selectSysRes = sysResMapper.getByUrl(url);
        for (SysRes menu : allMenuList) {
            if (menu.getPid() == null) {
                if (StringUtils.notBlank(menu.getUrl()) && menu.getUrl().equals(selectSysRes.getUrl())) {
                    sbBuilder.append("<li class=\"active\">");
                } else if (selectSysRes.getPid() != null && selectSysRes.getPid().equals(menu.getId())) {
                    sbBuilder.append("<li class=\"active open hsub\">");
                } else {
                    sbBuilder.append("<li class=\"\">");
                }
                if (StringUtils.notBlank(menu.getUrl()) && !menu.getUrl().equals("#")) {
                    sbBuilder.append("<a url=\"" + menu.getUrl() + "\" href=\"javascript:;\" data-index=\""
                            + menu.getId() + "\">");
                } else {
                    sbBuilder.append("<a href=\"#\" class=\"dropdown-toggle\">");
                }
                sbBuilder.append("<i class=\"menu-icon fa " + menu.getIconCls() + "\"></i>");
                sbBuilder.append("<span class=\"menu-text\">" + menu.getName() + "</span>");
                
                if (StringUtils.notBlank(menu.getUrl()) && !menu.getUrl().equals("#")) {
                    sbBuilder.append("</a>");
                } else {
                    sbBuilder.append("<b class=\"arrow fa fa-angle-down\"></b></a>");
                }
                sbBuilder.append("<b class=\"arrow\"></b>");
            }
            List<SysRes> childs = subMapList.get(menu.getId());
            if (childs != null && childs.size() > 0) {// ����
                sbBuilder.append("<ul class=\"submenu\">");
                for (SysRes subMenu : childs) {
                    if (selectSysRes != null && subMenu.getUrl().equals(selectSysRes.getUrl())) {
                        sbBuilder.append("<li class=\"active\">");
                    } else {
                        sbBuilder.append("<li class=\"\">");
                    }
                    sbBuilder.append("<a url=\"" + subMenu.getUrl().substring(1, subMenu.getUrl().length()) + "\" data-index=\"" + menu.getId()
                            + "\" href=\"javascript:;\">");
                    sbBuilder.append("<i class=\"menu-icon fa fa-caret-right\"></i>");
                    sbBuilder.append(subMenu.getName() + "</a><b class=\"arrow\"></b></li>");
                }
                sbBuilder.append("</ul>");
            }
            
            sbBuilder.append("</li>");
        }
        sbBuilder.append("</>");
        return sbBuilder.toString();
    }
    
    /**
     * @param uid  �û�ID
     * @param type �˵�|����
     * @return
     */
    @Override
    public List<SysRes> getSysResList(int uid, Integer type) {
        List<SysRes> resList = new ArrayList<>();
        List<SysRole> sysRoleIds = sysRoleMapper.getSysRoleIdList(uid);
        if (sysRoleIds.size() == 0)
            return null;
        StringBuilder roleIds = new StringBuilder();
        boolean isAdmin = false;
        for (SysRole sysRole : sysRoleIds) {
            if (sysRole.getId().equals(1))
                isAdmin = true;
            roleIds.append(sysRole.getId()).append(",");
        }
        roleIds.deleteCharAt(roleIds.length() - 1);
        if (!isAdmin) {
            resList = sysResMapper.findResByRoleId(roleIds.toString(), type);
        } else {
            resList = sysResMapper.findResByAdmin(type);
        }
        return resList;
    }
    
}
