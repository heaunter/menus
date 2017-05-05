package com.vota.reservation.modules.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统字典管理Controller.
 */
@Controller("/dict")
public class DictController {

    /**
     * 分页查询字典组.
     *
     * @param request  the request
     * @param response the response
     * @param cursor   the cursor
     * @param size     the size
     * @return the model and view
     */
    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public ModelAndView queryDictGroup(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam(name = "cursor", defaultValue = "0") int cursor,
                                       @RequestParam(name = "size", defaultValue = "10") int size) {
        ModelAndView modelView = new ModelAndView();


        return modelView;
    }

    /**
     * 分页查询字典组.
     *
     * @param request     the request
     * @param response    the response
     * @param dictGroupId the dict group id
     * @return model and view
     */
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public ModelAndView queryDict(HttpServletRequest request, HttpServletResponse response,
                                  @PathVariable("id") String dictGroupId) {
        ModelAndView modelView = new ModelAndView();

        return modelView;
    }

}
