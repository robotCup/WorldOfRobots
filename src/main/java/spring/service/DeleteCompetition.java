package spring.service;

import java.util.List;
import java.util.TimerTask;

import spring.model.Competition;
import spring.model.User;

public class DeleteCompetition extends TimerTask {
	
	private int id_competition;
	private CompetitionService competitionService;
	private UserService userService;
	public DeleteCompetition(int id, CompetitionService competitionService, UserService userService){
		this.id_competition=id;
		this.competitionService=competitionService;
		this.userService=userService;
	}
	@Override
	public void run() {
		System.out.println("thread enclenché");
		List<User> users =this.competitionService.findUserByCompetition(this.id_competition);
		Competition competition=this.competitionService.findById(this.id_competition);
		if (competition.getStart_date()==null || users.size()<2 || competition.getClose_participate()==false && competition.getStart_date()!=null){
		this.competitionService.deleteCompetition(competition);
		String message = "La competition " + this.id_competition + " a été supprimé car le créateur n'a pas terminé sa création";
		for (int i = 0; i<users.size(); i++){
			this.userService.createMessage(users.get(i).getId(), message);
		}
		this.userService.createMessage(competition.getId_user(),"Votre compétition a été supprimé car vous n'avez pas cloturé les votes ou les inscriptions");
		}

	}

}
