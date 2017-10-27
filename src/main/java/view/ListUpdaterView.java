package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import controller.Controller;
import model.EventRequest;
import model.Position;
import model.Status;
import model.User;


public class ListUpdaterView extends JFrame {

    private final Controller controller;

    //private String [] status = {"Created by CS","Accepted by SCS","Accepted by FM", "Accepted by AM"};


    JPanel panel = new JPanel();
    private JTextField eventName;
    private JTextField dateFrom;
    JButton acceptButton = new JButton("Accept");
    JButton updateButton = new JButton("Update");
    JButton rejectButton = new JButton("Reject");

    private JLabel lblDate;
    private JTextField budget;
    private JLabel lblBudget;
    private JTextPane description;
    private JLabel lblDescription;
    private JTextField dateTo;
    private JLabel lblDateTo;
    private JList eventList;
    private JTextField clientInfo;
    private JLabel statusLabel;
    private JTextField currentStatus;
    private int currentEventId;
    private User currentUser;
    private Status status;


    public ListUpdaterView(Controller controller, User currentUser) {
        super("SEP List updater page");
        setSize(800, 600);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int xPos = (dim.width / 2) - (this.getWidth() / 2);
        int yPos = (dim.height / 2) - (this.getHeight() / 2);
        setLocation(xPos, yPos);
        panel.setLayout(null);

        this.controller = controller;
        this.currentUser = currentUser;


        getContentPane().add(panel);

        eventName = new JTextField();
        eventName.setBounds(164, 10, 114, 19);
        panel.add(eventName);
        eventName.setColumns(10);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(47, 12, 70, 15);
        panel.add(lblName);

        dateFrom = new JTextField();
        dateFrom.setBounds(164, 41, 114, 19);
        panel.add(dateFrom);
        dateFrom.setColumns(10);

        lblDate = new JLabel("From");
        lblDate.setBounds(47, 39, 99, 15);
        panel.add(lblDate);

        budget = new JTextField();
        budget.setBounds(164, 91, 114, 19);
        panel.add(budget);
        budget.setColumns(10);

        lblBudget = new JLabel("Budget");
        lblBudget.setBounds(47, 93, 70, 15);
        panel.add(lblBudget);

        description = new JTextPane();
        description.setBounds(164, 122, 119, 88);
        panel.add(description);

        lblDescription = new JLabel("Description");
        lblDescription.setBounds(47, 120, 99, 15);
        panel.add(lblDescription);


        JLabel eventsLabel = new JLabel("Events");
        eventsLabel.setBounds(328, 32, 70, 15);
        panel.add(eventsLabel);


        acceptButton.setBounds(61, 314, 117, 25);
        panel.add(acceptButton);

        dateTo = new JTextField();
        dateTo.setBounds(164, 64, 114, 19);
        panel.add(dateTo);
        dateTo.setColumns(10);

        lblDateTo = new JLabel("To");
        lblDateTo.setBounds(47, 66, 70, 15);
        panel.add(lblDateTo);

        List<EventRequest> event = controller.getEvents();
        EventRequest[] events = event.toArray(new EventRequest[event.size()]);

        eventList = new JList(events);
        panel.add(eventList);
        eventList.setBounds(356, 59, 228, 167);
        JScrollPane pane = new JScrollPane();

        getContentPane().add(pane, BorderLayout.NORTH);


        JLabel clientLabel = new JLabel("Client");
        clientLabel.setBounds(47, 224, 70, 15);
        panel.add(clientLabel);

        clientInfo = new JTextField();
        clientInfo.setBounds(164, 222, 114, 19);
        panel.add(clientInfo);
        clientInfo.setColumns(10);


        updateButton.setBounds(237, 314, 117, 25);
        panel.add(updateButton);


        rejectButton.setBounds(413, 314, 117, 25);
        panel.add(rejectButton);

        statusLabel = new JLabel("Status");
        statusLabel.setBounds(47, 267, 70, 15);
        panel.add(statusLabel);

        currentStatus = new JTextField();
        currentStatus.setBounds(164, 275, 398, 15);
        panel.add(currentStatus);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addListMouseListener(eventList);


        addListMouseListener(eventList);
        //viewEvent();
        acceptEventListener();
        updateEventListener();
        rejectEventAction();
        setVisible(true);


    }





    /*public void viewEvent(){
        switch ( currentUser.getPosition()) {
			case SeniorCustomerService:
				if(event.getText()!=status.Created.toString()){
					eventList.;
				}

			case FinancialManager:

				if (currentStatus.getText() != status.AcceptedBySCS.toString()) {
					eventList.setVisible(false);
				}

			case AdministrationManager:
				if (currentStatus.getText() != status.AcceptedByFM.toString()) {
					eventList.setVisible(false);
				}


		}
	}*/


    public void addListMouseListener(JList list) {
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                EventRequest event = (EventRequest) list.getModel().getElementAt(index);
                eventName.setText(event.getName());
                dateFrom.setText(event.getFrom());
                dateTo.setText(event.getTo());
                budget.setText(String.valueOf(event.getBudget()));
                description.setText(event.getDescription());
                clientInfo.setText(event.getClient().getName());
                //Status status1 = Status.Created;
                currentStatus.setText(event.getStatus().getText());
                //event.setStatus(status.getText());
                currentEventId = event.getId();

/*
                if (currentUser.getPosition() == Position.SeniorCustomerService && event.getStatus() == status) {
                    acceptButton.setVisible(true);
                    rejectButton.setVisible(true);
                }

                if (currentUser.getPosition() == Position.FinancialManager && event.getStatus() == status.Created) {
                    acceptButton.setVisible(true);
                    eventList.clearSelection();
                }
                else if (currentUser.getPosition()==Position.FinancialManager&& event.getStatus()==status.AcceptedBySCS){
                    //currentStatus.setText(Status.AcceptedBySCS.toString());
                    rejectButton.setVisible(true);
                    updateButton.setVisible(true);



                }

                if (currentUser.getPosition() == Position.AdministrationManager && event.getStatus() == status.AcceptedByFM) {
                    //setVisible(true);
                    acceptButton.setVisible(true);
                    rejectButton.setVisible(true);
                }

                if (currentUser.getPosition() == Position.FinancialManager && event.getStatus() == status.AcceptedByAM) {
                    updateButton.setVisible(true);
                    updateEventListener();
                }*/

            }

        });
    }

    public void acceptEventListener() {
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.changeStatus(currentEventId,currentUser);
               /*Status newStatus;
                switch (currentUser.getPosition()) {
                    case SeniorCustomerService:
                        if(currentStatus==null){
                            currentStatus.setText(status.getText());
                        }
                        newStatus = Status.AcceptedBySCS;
                        currentStatus.setText(newStatus.toString());
                        break;
                    case FinancialManager:
                        if(status == status.Created){
                            currentStatus.setText(status.getText().toString());
                        }
                        else if(status==status.AcceptedBySCS) {

                            currentStatus.setText(status.getText().toString());
                           status = Status.AcceptedByFM;
                           currentStatus.setText(status.getText());
                        }
                        currentStatus.setText(status.AcceptedByFM.getText());
                        break;
                    case AdministrationManager:
                        newStatus = status.AcceptedByAM;
                        currentStatus.setText(newStatus.toString());
                        break;
                    default:
                        newStatus = null;
                        currentStatus.setText(newStatus.toString());*/
                }


        });
    }

    public void updateEventListener() {
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double newBudget = Double.valueOf(budget.getText());
                EventRequest event = (EventRequest) eventList.getSelectedValue();
                controller.updateEvent(newBudget, event);
            }
        });
    }

    public void rejectEventAction() {
        rejectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventRequest event = (EventRequest) eventList.getSelectedValue();
                controller.deleteEvent(event);
            }
        });
    }


}
