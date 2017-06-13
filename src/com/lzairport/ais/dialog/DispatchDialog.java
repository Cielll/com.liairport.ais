package com.lzairport.ais.dialog;

import java.util.Date;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.service.aodb.IFlightService;
import com.lzairport.ais.tableviewer.celldata.FlightDisPatchCell;
import com.lzairport.ais.tableviewer.celldata.ICell;
import com.lzairport.ais.tableviewer.header.HeaderItem;



/**
 * ��������ȡ�����Ȼ��ڵ�ʱ��ĶԻ���
 * @author ZhangYu
 * @since JDK1.6 
 * @version 0.9a 0.9a 12/07/14
 *
 */


public class DispatchDialog extends Dialog {
	


	private IFlightService<Integer,? extends Flight> flightService;
	
	private Flight flight;
	
	private HeaderItem field;
	
	private Dialog dialog;
	
	private ICell cell;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	

	public DispatchDialog(IFlightService<Integer,? extends Flight> flightService,
			Flight flight, HeaderItem field) {
		super(Display.getDefault().getActiveShell());
		this.flightService = flightService;
		this.flight = flight;
		this.field = field;
		this.cell = FlightDisPatchCell.getInstance();
		dialog = this;
	}



	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);
		
		//�Ե�ǰʱ�䷢��
		Button btnNow = new Button(container, SWT.NONE);
		btnNow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cell.setData(flight, field, new Date(),null);
				flightService.update(flight);
				dialog.close();
			}
		});
		btnNow.setBounds(36, 30, 139, 40);
		btnNow.setText("\u4EE5\u5F53\u524D\u65F6\u95F4\u53D1\u5E03");
		
		//�Զ���ʱ�䷢��
		Button btnCustom = new Button(container, SWT.NONE);
		btnCustom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dialog.close();
				TimeDialog dialog = new TimeDialog("������"+field.getName()+"�ķ���ʱ��",new Date());
				dialog.open();
				Date dispatchTime = dialog.getDate();
				if (dispatchTime != null){
					cell.setData(flight, field, dispatchTime,null);
					flightService.update(flight);
				}
			}
		});
		btnCustom.setText("\u624B\u5DE5\u53D1\u5E03\u65F6\u95F4");
		btnCustom.setBounds(216, 30, 139, 40);
		
		//ȡ������ʱ��
		Button btnCnl = new Button(container, SWT.NONE);
		btnCnl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cell.setData(flight, field, null,null);
				flightService.update(flight);	
				dialog.close();
			}
		});
		btnCnl.setText("\u53D6\u6D88\u53D1\u5E03\u65F6\u95F4");
		btnCnl.setBounds(387, 30, 139, 40);

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(573, 142);
	}

}
