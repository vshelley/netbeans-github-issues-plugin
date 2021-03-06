/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2014 Sun Microsystems, Inc.
 */
package com.junichi11.netbeans.modules.github.issues.issue.ui;

import com.junichi11.netbeans.modules.github.issues.GitHubCache;
import com.junichi11.netbeans.modules.github.issues.repository.GitHubRepository;
import com.junichi11.netbeans.modules.github.issues.utils.DateUtils;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Label;
import org.eclipse.egit.github.core.Milestone;
import org.eclipse.egit.github.core.User;
import org.openide.util.NbBundle;

/**
 *
 * @author junichi11
 */
public class AttributesViewPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 8704520155275718422L;

    /**
     * Creates new form AttributesViewPanel
     */
    public AttributesViewPanel() {
        initComponents();
        Font assigneeFont = assigneeNameLabel.getFont();
        Font bold = new Font(assigneeFont.getName(), Font.BOLD, assigneeFont.getSize());
        assigneeNameLabel.setFont(bold);
        milestoneNameLabel.setFont(bold);
    }

    @NbBundle.Messages({
        "AttributesViewPanel.LBL.dueDate=Due date"
    })
    public void setAttributes(Issue issue, GitHubRepository repository) {
        initAttributes();
        if (issue == null) {
            return;
        }

        // user
        User assignee = issue.getAssignee();
        if (assignee != null) {
            String login = assignee.getLogin();
            assigneeNameLabel.setText(login);
            assigneeNameLabel.setToolTipText(login);
            if (repository != null) {
                GitHubCache cache = GitHubCache.create(repository);
                assigneeNameLabel.setIcon(cache.getUserIcon(assignee));
            }
        }

        // milestone
        Milestone milestone = issue.getMilestone();
        if (milestone != null) {
            milestoneNameLabel.setText(milestone.getTitle());

            // tooptip
            Date dueDate = milestone.getDueOn();
            String description = milestone.getDescription();
            StringBuilder sb = new StringBuilder();
            sb.append("<html>"); // NOI18N
            if (description == null) {
                description = ""; // NOI18N
            }
            description = description.replaceAll("\n", "<br>"); // NOI18N
            if (dueDate != null) {
                sb.append(Bundle.AttributesViewPanel_LBL_dueDate()).append(" : ").append(DateUtils.DEFAULT_DATE_FORMAT.format(dueDate)).append("<br>"); // NOI18N
                sb.append("<hr>"); // NOI18N
            }
            sb.append(description);
            sb.append("</html>"); // NOI18N
            milestoneNameLabel.setToolTipText(sb.toString());
        }

        List<Label> labels = issue.getLabels();
        for (Label label : labels) {
            String name = label.getName();
            JLabel l = new JLabel(String.format("<html><b>%s</b></html>", name)); // NOI18N
            l.setToolTipText(name);
            l.setOpaque(true);
            Color bgColor = Color.decode(String.format("#%s", label.getColor())); // NOI18N
            l.setBackground(bgColor);
            l.setForeground(getForeground(bgColor));
            EmptyBorder emptyBorder = new EmptyBorder(3, 5, 3, 5);
            l.setBorder(emptyBorder);
            labelsPanel.add(l);
        }
    }

    private void initAttributes() {
        assigneeNameLabel.setText("-"); // NOI18N
        milestoneNameLabel.setText("-"); // NOI18N
        labelsPanel.removeAll();
    }

    private static int getBrightness(Color color) {
        return (color.getRed() * 299 + color.getGreen() * 578 + color.getBlue() * 114) / 1000;
    }

    private static Color getForeground(Color bgColor) {
        int brightness = getBrightness(bgColor);
        return brightness < 130 ? Color.WHITE : Color.BLACK;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        assigneeLabel = new javax.swing.JLabel();
        assigneeNameLabel = new javax.swing.JLabel();
        milestoneLabel = new javax.swing.JLabel();
        milestoneNameLabel = new javax.swing.JLabel();
        labelsLabel = new javax.swing.JLabel();
        labelsPanel = new javax.swing.JPanel();

        org.openide.awt.Mnemonics.setLocalizedText(assigneeLabel, org.openide.util.NbBundle.getMessage(AttributesViewPanel.class, "AttributesViewPanel.assigneeLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(assigneeNameLabel, org.openide.util.NbBundle.getMessage(AttributesViewPanel.class, "AttributesViewPanel.assigneeNameLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(milestoneLabel, org.openide.util.NbBundle.getMessage(AttributesViewPanel.class, "AttributesViewPanel.milestoneLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(milestoneNameLabel, org.openide.util.NbBundle.getMessage(AttributesViewPanel.class, "AttributesViewPanel.milestoneNameLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(labelsLabel, org.openide.util.NbBundle.getMessage(AttributesViewPanel.class, "AttributesViewPanel.labelsLabel.text")); // NOI18N

        labelsPanel.setLayout(new java.awt.GridLayout(0, 5, 2, 2));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(assigneeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(assigneeNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(milestoneLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(milestoneNameLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(assigneeLabel)
                    .addComponent(assigneeNameLabel)
                    .addComponent(milestoneLabel)
                    .addComponent(milestoneNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelsLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(labelsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel assigneeLabel;
    private javax.swing.JLabel assigneeNameLabel;
    private javax.swing.JLabel labelsLabel;
    private javax.swing.JPanel labelsPanel;
    private javax.swing.JLabel milestoneLabel;
    private javax.swing.JLabel milestoneNameLabel;
    // End of variables declaration//GEN-END:variables
}
