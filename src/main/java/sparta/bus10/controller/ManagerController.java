package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.bus10.security.UserDetailsImpl;
import sparta.bus10.service.manager.ManagerService;

@RestController
@RequestMapping("/api/managers")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_USER')")
public class ManagerController {
    private final ManagerService managerService;

    // 매니저 신청 목록 조회
    @GetMapping
    public void getApplyList(){
        // adminService.getApplyList();
    }

    // 매니저 등업 신청
    @PostMapping("/submit")
    public void upgradeUser(@AuthenticationPrincipal UserDetailsImpl userDetails){
        managerService.upgradeUser(userDetails.getUser());
    }
}
