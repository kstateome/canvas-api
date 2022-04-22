package edu.ksu.canvas.requestOptions;

import java.util.Date;
import java.util.List;

import edu.ksu.canvas.requestOptions.BaseOptions;
import edu.ksu.canvas.requestOptions.GetSubmissionsOptions.Include;

public class GetMultipleSubmissionsOptions extends BaseOptions {
	
	private String canvasId;
	
	/**
     * Construct options class with required parameters to retrieve a list of multiple Submissions from courses or sections
     * @param canvasId The Course or Section ID, depending on which API is being targeted.
     */
    public GetMultipleSubmissionsOptions(final String canvasId) {
        this.canvasId = canvasId;
    }
	

	/**
     * Filter the list of submissions by the given student ids..
     * @param ids List of ids to filter submissions by
     * @return This object to allow adding more options
     */
    public GetMultipleSubmissionsOptions studentIds(final List<String> ids) {
        optionsMap.put("student_ids[]", ids);
        return this;
    }
    
    /**
     * Optionally include more information with the returned assignment Submission objects.
     * @param includes List of optional includes
     * @return This object to allow adding more options
     */
    public GetMultipleSubmissionsOptions includes(final List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }
    
    /**
     * List of assignments to return submissions for. 
     * If none are given, submissions for all assignments are returned.
     * @param ids List of ids to filter submissions by
     * @return This object to allow adding more options
     */
    public GetMultipleSubmissionsOptions assignmentIds(final List<String> ids) {
    	optionsMap.put("assignment_ids[]", ids);
        return this;
    }
    
    /**
     * When set to true, response will be grouped by student groups.
     * Only valid for Submission lists, not individual submission queries.
     * @param grouped Whether to group submissions by student group
     * @return This object to allow adding more options
     */
    public GetMultipleSubmissionsOptions grouped(Boolean grouped) {
        addSingleItem("grouped", Boolean.toString(grouped));
        return this;
    }
    
    /**
     * If this argument is set to true, the response will only include submissions for assignments 
     * that have the post_to_sis flag set to true and user enrollments that were added through sis.
     * @param postToSis Whether to include submissions that have the flag active
     * @return This object to allow adding more options
     */
    public GetMultipleSubmissionsOptions postToSis(Boolean postToSis) {
        addSingleItem("post_to_sis", Boolean.toString(postToSis));
        return this;
    }
    
    /**
     * If this argument is set, the response will only include submissions that were submitted 
     * after the specified date_time. 
     * This will exclude submissions that do not have a submitted_at which will exclude unsubmitted submissions
     * @param submittedSince date to get submissions after
     * @return This object to allow adding more options
     */
    public GetMultipleSubmissionsOptions submittedSince(Date submittedSince) {
        addSingleItem("submitted_since", submittedSince.toString());
        return this;
    }
    
    /**
     * If this argument is set, the response will only include submissions that were graded 
     * after the specified date_time. 
     * This will exclude submissions that have not been graded.
     * @param gradedSince date to get submissions graded after
     * @return This object to allow adding more options
     */
    public GetMultipleSubmissionsOptions gradedSince(Date gradedSince) {
        addSingleItem("graded_since", gradedSince.toString());
        return this;
    }
    
    /**
     * The id of the grading period in which submissions are being requested 
     * (Requires grading periods to exist on the account)
     * @param gradingPeriodId the grading period for the submissions
     * @return This object to allow adding more options
     */
    public GetMultipleSubmissionsOptions gradingPeriodId(Integer gradingPeriodId) {
        addSingleItem("grading_period_id", gradingPeriodId.toString());
        return this;
    }
    
    /**
     * 	
	 * The current status of the submission
     * @param workflowState the status to get the submissions
     * @return This object to allow adding more options
     */
    public GetMultipleSubmissionsOptions workflowState(String workflowState) {
        addSingleItem("workflow_state", workflowState);
        return this;
    }
    
    /**
     * 	
	 * The current state of the enrollments. 
	 * If omitted will include all enrollments that are not deleted.
     * @param enrollmentState the state of the enrollment to get submissions
     * @return This object to allow adding more options
     */
    public GetMultipleSubmissionsOptions enrollmentState(String enrollmentState) {
        addSingleItem("enrollment_state", enrollmentState);
        return this;
    }
    
    /**
     * If omitted it is set to true. When set to false it will ignore the effective state of the student enrollments 
     * and use the workflow_state for the enrollments. 
     * The argument is ignored unless enrollment_state argument is also passed.
     * @param stateBasedOnDate the state of the enrollment to get submissions
     * @return This object to allow adding more options
     */
    public GetMultipleSubmissionsOptions stateBasedOnDate(Boolean stateBasedOnDate) {
        addSingleItem("state_based_on_date", stateBasedOnDate.toString());
        return this;
    }
    
    /**
     * The order submissions will be returned in. 
     * Defaults to “id”. Doesn't affect results for “grouped” mode.
     * @param order the order indicated
     * @return This object to allow adding more options
     */
    public GetMultipleSubmissionsOptions order(String order) {
        addSingleItem("order", order);
        return this;
    }
    
    /**
     * Determines whether ordered results are returned in ascending or descending order. 
     * Defaults to “ascending”. 
     * Doesn't affect results for “grouped” mode.
     * @param orderDirection "ascending" or "descending"
     * @return This object to allow adding more options
     */
    public GetMultipleSubmissionsOptions orderDirection(String orderDirection) {
        addSingleItem("order_direction", orderDirection);
        return this;
    }


	public String getCanvasId() {
		return canvasId;
	}


	public void setCanvasId(String canvasId) {
		this.canvasId = canvasId;
	}
}
