/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.rte.rdt.plugin.editor;

import java.io.File;
import java.io.FileNotFoundException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import egovframework.rte.rdt.plugin.Activator;
import egovframework.rte.rdt.plugin.message.Messages;
import egovframework.rte.rdt.plugin.model.DependencyContentProvider;
import egovframework.rte.rdt.plugin.model.DependencyLabelProvider;
import egovframework.rte.rdt.plugin.model.TableList;
import egovframework.rte.rdt.plugin.preferences.RdtPreferencePage;
import egovframework.rte.rdt.plugin.util.ProjectUtil;
import egovframework.rte.rdt.service.unit.Service;

/**
 * ???????????? ???????????? UI??? ?????? ?????????
 * @author ?????????
 */
public class DistributionToolPage implements Page {

	/** ????????? ???????????? ????????? */
	private Table installedTable;
	/** ???????????? ???????????? ????????? */
	private Table notInstalledTable;
	/** ????????? ???????????? ??????????????? */
	private CheckboxTableViewer insTableViewer;
	/** ??? ????????? ???????????? ??????????????? */
	private CheckboxTableViewer notInsTableViewer;
	/** UI??? section */
	private Section section;
	/** Maven:Install?????? ???????????? ?????? ???????????? */
	private Button chkMavenInstall;
	/** chkMavenInstall??? ?????? ?????????????????? ???????????? */
	private String CHK_MVN_INSTALL = "CHK_MVN_INS";
	/** ????????? ???????????? ???????????? ???????????? ?????? */
	private TableList tableList;
	/** ???????????? ?????? ?????? ?????? */
	private RdtEditor rdtEditor;
	/** Preference??? ????????? ???????????? ?????? Store */
	private IPreferenceStore store;
	
	/**
	 * ?????????
	 * @param rdtEditor
	 */
	public DistributionToolPage(RdtEditor rdtEditor) {
		this.rdtEditor = rdtEditor;
		tableList = new TableList(rdtEditor);
		store = Activator.getDefault().getPreferenceStore();
	}

	/**
	 * ????????? ???????????? ???????????? ??????
	 * @param parent ????????? ??? Composite
	 * @return Composite 
	 */
	public Composite getPage(final Composite parent) {

		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		ScrolledForm form = toolkit.createScrolledForm(parent);
		form.setText(Messages.DistributionToolPage_1); // Installation
		form.getBody().setLayout(new FillLayout());
		toolkit.decorateFormHeading(form.getForm());
		
		Composite container = toolkit.createComposite(form.getBody(), SWT.NONE);
		container.setLayout(new FillLayout());
		
		createComponent(toolkit, form, container);

		insTableViewer.setLabelProvider(new DependencyLabelProvider());
		insTableViewer.setContentProvider(new DependencyContentProvider());
		insTableViewer.setInput(tableList.getInstalledList());
		notInsTableViewer.setLabelProvider(new DependencyLabelProvider());
		notInsTableViewer.setContentProvider(new DependencyContentProvider());
		notInsTableViewer.setInput(tableList.getNotInstalledList());
		return form;
	}
	
	/**
	 * ????????? ???????????? ????????????, ??????????????? ????????????.
	 * @param toolkit
	 * @param form
	 * @param parent
	 */
	public void createComponent(FormToolkit toolkit, final ScrolledForm form, Composite parent) {
		section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.marginWidth = 10;
		section.marginHeight = 5;
		section.setText(Messages.DistributionToolPage_2);

		Composite container = toolkit.createComposite(section, SWT.NONE);
        GridLayout grid = new GridLayout(1,false);
        container.setLayout(grid);
		section.setClient(container);
		toolkit.paintBordersFor(container);
		
		
		Group grpInstalled = new Group(container, SWT.NONE);
		grpInstalled.setBackground(getColor(SWT.COLOR_WHITE));
		grpInstalled.setText(Messages.DistributionToolPage_3);
		
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.heightHint  = 512;
		
		grpInstalled.setLayoutData(gridData);
		grpInstalled.setLayout(new FillLayout());
		
		insTableViewer = CheckboxTableViewer.newCheckList(grpInstalled,
				SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK );
		installedTable = insTableViewer.getTable();
		installedTable.setHeaderVisible(true);
		installedTable.setLinesVisible(true);
		
		String[] InstalledColumnNames = new String[]{
				"", Messages.DistributionToolPage_5, Messages.DistributionToolPage_6, Messages.DistributionToolPage_7, Messages.DistributionToolPage_8 };
		int[] InstalledColumnWidths = new int[]{ 30, 100, 190, 100, 100 };

		String[] NotInstalledColumnNames = new String[]{
				"", Messages.DistributionToolPage_5, Messages.DistributionToolPage_6, Messages.DistributionToolPage_7 };
		int[] NotInstalledColumnWidths = new int[]{	30, 120, 200, 100 };
		
		for(int i = 0;i < InstalledColumnNames.length;i++){
			TableColumn tableColumn = new TableColumn(installedTable, SWT.LEFT);
			tableColumn.setText(InstalledColumnNames[i]);
			tableColumn.setWidth(InstalledColumnWidths[i]);
		}
		
		//????????? ???????????? ??????????????? ????????? ????????? install
		Composite installContainer = toolkit.createComposite(container, SWT.NONE);
		installContainer.setLayout(new GridLayout(2,false));
		installContainer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnUpdate = new Button(installContainer,  SWT.NONE);
		btnUpdate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnUpdate.setText(Messages.DistributionToolPage_14);
		
		Button btnUninstall = new Button(installContainer,  SWT.NONE);
		btnUninstall.setText(Messages.DistributionToolPage_15);
		
		Group grpNotInstalled = new Group(container, SWT.NONE);
		grpNotInstalled.setBackground(getColor(SWT.COLOR_WHITE));
		grpNotInstalled.setText(Messages.DistributionToolPage_13);
		grpNotInstalled.setLayoutData(gridData);
		grpNotInstalled.setLayout(new FillLayout());
		
		notInsTableViewer = CheckboxTableViewer.newCheckList(grpNotInstalled, SWT.BORDER | SWT.FULL_SELECTION);
		notInstalledTable = notInsTableViewer.getTable();
		notInstalledTable.setLinesVisible(true);
		notInstalledTable.setHeaderVisible(true);
		
		for(int i = 0;i < NotInstalledColumnNames.length;i++){
			TableColumn tableColumn = new TableColumn(notInstalledTable, SWT.LEFT);
			tableColumn.setText(NotInstalledColumnNames[i]);
			tableColumn.setWidth(NotInstalledColumnWidths[i]);
		}

		//???????????? ???????????? ??????????????? ????????? ????????? notinstall
		Composite notInstallContainer = toolkit.createComposite(container, SWT.END);
		notInstallContainer.setLayout(new GridLayout(2, false));
		notInstallContainer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnInstall = new Button(notInstallContainer, SWT.NONE);
		btnInstall.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnInstall.setText(Messages.DistributionToolPage_16);
		
		Button btnRefresh = new Button(notInstallContainer, SWT.NONE);
		btnRefresh.setText(Messages.DistributionToolPage_17);
		
		chkMavenInstall = new Button(container, SWT.CHECK);
		chkMavenInstall.setBackground(getColor(SWT.COLOR_WHITE));

		//property?????? ???????????? ??????????????? ?????????
		try {
			String flagMvnIns = 
			rdtEditor.getCurrentProject().getPersistentProperty(
				new QualifiedName("", CHK_MVN_INSTALL));
			chkMavenInstall.setSelection(flagMvnIns=="true"?true:false);
		} catch (CoreException ce) {
			ce.printStackTrace();
		}
		chkMavenInstall.setBounds(43, 507, 191, 25);
		chkMavenInstall.setText(Messages.DistributionToolPage_20);
		
		
		/*************************************************************************/
		/************************   Install Listener   ***************************/
		/*************************************************************************/
		/* Installed table checkbox listener*/
		chkMavenInstall.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					rdtEditor.getCurrentProject().setPersistentProperty(
						new QualifiedName("", CHK_MVN_INSTALL),
						chkMavenInstall.getSelection()?"true":"false"); //$NON-NLS-2$
				} catch (CoreException ce) {
					ce.printStackTrace();
				}
			}
		});
		
		/* Install button listener*/
		btnInstall.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(tableList.getNotInstalledList() == null)
					return;
				
				for(Object o : tableList.getNotInstalledList()) {
					Service s = (Service)o;
					if ("Core".equals(s.getLayer())) {
						notInsTableViewer.setChecked(s, true);
					}
				}
				tableList.InstallDependency(notInsTableViewer.getCheckedElements());
				refreshPage();
				mavenInstall();
			}
		});
		
		/* Uninstall button listener*/
		btnUninstall.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean errorMessageFlag = true;
				for(Object o : insTableViewer.getCheckedElements()) {
					Service s = (Service)o;
					if ("Core".equals(s.getLayer())) {
						if(errorMessageFlag) {
							ProjectUtil.errorBox(rdtEditor.getShell(),
								Messages.DistributionToolPage_26 + ProjectUtil.ENTER + Messages.DistributionToolPage_27);
							errorMessageFlag = false;
						}
						insTableViewer.setChecked(s, false);
					}
				}
				if(errorMessageFlag || insTableViewer.getCheckedElements() != null) {
					tableList.UninstallDependency(insTableViewer.getCheckedElements());
					insTableViewer.remove(insTableViewer.getCheckedElements());
					insTableViewer.setAllChecked(true);
					tableList.InstallDependency(insTableViewer.getCheckedElements());
				}
				refreshPage();
				mavenInstall();
			}
		});
		
		/* Update button listener*/
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableList.InstallDependency(insTableViewer.getCheckedElements());
				refreshPage();
				
				mavenInstall();
			}
		});
		
		/* Refresh button listener*/
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshPage();
			}
		});
	}

	/**
	 * ???????????? ?????? ?????????.
	 */
	public void refreshPage() {
		tableList = new TableList(rdtEditor);
		insTableViewer.setInput(tableList.getInstalledList());
		insTableViewer.refresh();

		notInsTableViewer.setInput(tableList.getNotInstalledList());
		notInsTableViewer.refresh();

		// ???????????? refresh
		try {
			rdtEditor.getCurrentProject().refreshLocal(	IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Maven??? Install ????????? ???????????????.
	 */
	public void mavenInstall()
	{
		if(chkMavenInstall.getSelection()){
			if(store.getString(RdtPreferencePage.MVN_PROPERTY) != null){
				try {
					String mvnLocation = store.getString(RdtPreferencePage.MVN_PROPERTY);
					String mvnExecFile = mvnLocation + ProjectUtil.SLASH + "bin" + ProjectUtil.SLASH +Messages.DistributionToolPage_29; //mvn.bat
					
					File file = new File(mvnExecFile);
					if(!file.exists()){
						throw new FileNotFoundException(mvnExecFile);
					}
					
					ILaunchManager launchManager = DebugPlugin.getDefault()
						.getLaunchManager();

					ILaunchConfigurationType type = launchManager
						.getLaunchConfigurationType(org.eclipse.ui.externaltools.internal.model.IExternalToolConstants.ID_PROGRAM_LAUNCH_CONFIGURATION_TYPE);

					ILaunchConfigurationWorkingCopy workingCopy = type.newInstance(null, "rdt install");
					
					workingCopy.setAttribute(
						org.eclipse.ui.externaltools.internal.model.IExternalToolConstants.ATTR_LOCATION,
						mvnExecFile);
					workingCopy.setAttribute(
						org.eclipse.ui.externaltools.internal.model.IExternalToolConstants.ATTR_TOOL_ARGUMENTS,
						"install");
					workingCopy.setAttribute(
						org.eclipse.ui.externaltools.internal.model.IExternalToolConstants.ATTR_WORKING_DIRECTORY,
						rdtEditor.getCurrentProject().getLocation().toOSString());

					ILaunchConfiguration configuration = workingCopy.doSave();
					DebugUITools.launch(configuration, ILaunchManager.RUN_MODE);
				} catch(FileNotFoundException fne) {
					ProjectUtil.errorBox(rdtEditor.getShell(), Messages.DistributionToolPage_4);
				} catch (CoreException ce) {
					ProjectUtil.errorBox(rdtEditor.getShell(), Messages.DistributionToolPage_9 + ProjectUtil.ENTER + ce.getMessage());
				} catch (Exception e){
					ProjectUtil.errorBox(rdtEditor.getShell(), Messages.DistributionToolPage_9 + ProjectUtil.ENTER + e.getMessage()); 
				}
			}
			else {
				ProjectUtil.errorBox(installedTable,
				Messages.DistributionToolPage_32);
			}
		}
	}
	
	/**
	 * systemColorID??? ???????????? ?????? ????????????.
	 * @param systemColorID
	 * @return Color systemColorID??? ???????????? ???
	 */
	public Color getColor(int systemColorID) {
		Display display = Display.getCurrent();
		return display.getSystemColor(systemColorID);
	}
}
