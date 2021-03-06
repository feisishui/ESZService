package com.sinohealth.eszservice.dto.sick;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sinohealth.eszorm.entity.base.GradeEntity;
import com.sinohealth.eszservice.common.dto.BaseDto;

public class SickGradeDto extends BaseDto {

	private static final long serialVersionUID = -7706390085762658784L;

	private List<GradeEntity> list;

	private int totalGrade;

	@Override
	public String toString() {
		JSONObject jo = new JSONObject();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			jo.put("errCode", errCode);
			jo.put("errMsg", errMsg);

			// gradeInfo
			JSONObject sub = new JSONObject();

			sub.put("totalGrade", totalGrade);

			JSONArray gradeList = new JSONArray();
			if (null != list) {
				for (GradeEntity ent : list) {
					JSONObject o = new JSONObject();
					o.put("actionName",
							null != ent.getActionName() ? ent.getActionName()
									: "");
					o.put("actionTime",
							null != ent.getActionTime() ? format.format(ent
									.getActionTime()) : "");
					o.put("actionGrade", null != Integer.valueOf(ent
							.getActionGrade()) ? ent.getActionGrade() : 0);
					gradeList.put(o);
				}
				sub.put("list", gradeList);
			}

			jo.put("gradeInfo", sub);
		} catch (JSONException e) {
			return "{}";
		}
		return jo.toString();
	}

	public void setGrades(List<GradeEntity> list) {
		this.list = list;

	}

	public List<GradeEntity> getList() {
		return list;
	}

	public void setList(List<GradeEntity> list) {
		this.list = list;
	}

	public int getTotalGrade() {
		return totalGrade;
	}

	public void setTotalGrade(int totalGrade) {
		this.totalGrade = totalGrade;
	}

}
