package sparta.bus10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.bus10.entity.Apply;
import sparta.bus10.security.UserDetailsImpl;
import sparta.bus10.service.manager.ManagementService;

import java.util.List;

@RestController
@RequestMapping("/api/managers")
@RequiredArgsConstructor
public class ManagementController {

    private final ManagementService managementService;

    // 매니저 신청 목록 조회
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Apply> getApplyList(){
        return managementService.getApplyList();
    }

    // 매니저 등업 신청
    @PostMapping("/submit")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void applyManager(@AuthenticationPrincipal UserDetailsImpl userDetails){
        managementService.applyManager(userDetails.getUser());
    }

    // 매니저 신청 수락
    @PostMapping("/accept/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void acceptApply(@PathVariable Long userId){
        managementService.acceptApply(userId);
    }

    // 매니저 신청 거부
    @PostMapping("/decline/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void declineApply(@PathVariable Long userId){
        managementService.declineApply(userId);
    }
    
}
