package com.mossle.auth.web.auth;

import java.util.ArrayList;
import java.util.List;

import com.mossle.api.ScopeConnector;

import com.mossle.auth.component.RoleDefChecker;
import com.mossle.auth.domain.Perm;
import com.mossle.auth.domain.RoleDef;
import com.mossle.auth.manager.PermManager;
import com.mossle.auth.manager.RoleDefManager;
import com.mossle.auth.support.CheckRoleException;

import com.mossle.core.scope.ScopeHolder;
import com.mossle.core.struts2.BaseAction;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

@Results({ @Result(name = RolePermAction.RELOAD, location = "role-perm.do?id=${id}&operationMode=RETRIEVE", type = "redirect") })
public class RolePermAction extends BaseAction {
    public static final String RELOAD = "reload";
    private PermManager permManager;
    private RoleDefManager roleDefManager;
    private MessageSourceAccessor messages;
    private long id;
    private List<Long> selectedItem = new ArrayList<Long>();
    private List<Perm> perms;
    private RoleDefChecker roleDefChecker;
    private ScopeConnector scopeConnector;

    public String execute() {
        return input();
    }

    public String save() {
        try {
            RoleDef roleDef = roleDefManager.get(id);
            roleDefChecker.check(roleDef);
            roleDef.getPerms().clear();

            for (Long permId : selectedItem) {
                Perm perm = permManager.get(permId);
                roleDef.getPerms().add(perm);
            }

            roleDefManager.save(roleDef);
            addActionMessage(messages.getMessage("core.success.save", "保存成功"));
        } catch (CheckRoleException ex) {
            addActionMessage(ex.getMessage());

            return input();
        }

        return RELOAD;
    }

    public String input() {
        RoleDef roleDef = roleDefManager.get(id);

        for (Perm perm : roleDef.getPerms()) {
            selectedItem.add(perm.getId());
        }

        Long localId = scopeConnector.findLocalId(ScopeHolder.getGlobalCode(),
                ScopeHolder.getLocalCode());
        perms = permManager.findBy("localId", localId);

        return INPUT;
    }

    // ~ ======================================================================
    public void setPermManager(PermManager permManager) {
        this.permManager = permManager;
    }

    public void setRoleDefManager(RoleDefManager roleDefManager) {
        this.roleDefManager = roleDefManager;
    }

    public void setRoleDefChecker(RoleDefChecker roleDefChecker) {
        this.roleDefChecker = roleDefChecker;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    // ~ ======================================================================
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<Long> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<Perm> getPerms() {
        return perms;
    }

    public void setScopeConnector(ScopeConnector scopeConnector) {
        this.scopeConnector = scopeConnector;
    }
}
