package com.lichaoxi.bive.controller.admin;

import com.lichaoxi.bive.controller.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
@PreAuthorize("hasRole('ADMIN')")
public class Permission extends BaseController {

}
