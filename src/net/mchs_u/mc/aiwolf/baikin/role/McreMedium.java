package net.mchs_u.mc.aiwolf.baikin.role;

import java.util.Map;

import org.aiwolf.client.lib.TemplateTalkFactory;
import org.aiwolf.common.data.Judge;
import org.aiwolf.common.data.Role;

public class McreMedium extends McreVillager {
	public static final int PATTERN_MEDIUM = 0;
	
	private boolean co = false;
	private boolean inquestedToday = false;

	public McreMedium() {
		super();
	}
	
	public McreMedium(Map<String,Double> estimateRates) {
		super(estimateRates);
	}
	
	@Override
	public void dayStart() {
		super.dayStart();
		inquestedToday = false;
	}
	
	@Override
	public String talk() {
		//COしてない場合はCO
		if(!co){
			co = true;
			return TemplateTalkFactory.comingout(getMe(), Role.MEDIUM);
		}
		
		//今日霊能結果を言ってなくて霊能結果があれば霊能結果を言う
		Judge j = getLatestDayGameInfo().getMediumResult();
		if(!inquestedToday && j != null){
			inquestedToday = true;
			subjectiveEstimate.updateDefinedSpecies(j.getTarget(), j.getResult());//自分目線に霊能情報を更新
			return TemplateTalkFactory.inquested(j.getTarget(), j.getResult());
		}
		
		//村人と同じtalk
		return super.talk();
	}
}
