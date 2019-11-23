package com.zxk.admin.biz.service;

import com.zxk.admin.client.service.HallowService;
import org.apache.dubbo.config.annotation.Service;


/**
 * TODO
 *
 * @author xiaokun.zhang
 * Date:   2019年11月22日 17:32
 * @version 1.0
 */
@Service
public class HallowServiceImpl implements HallowService {

    @Override
    public void aaa(){
        System.out.println(1111);
    }
}
