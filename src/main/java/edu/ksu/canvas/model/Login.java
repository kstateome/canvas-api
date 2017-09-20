package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.*;

import java.io.Serializable;

/**
 * Class to represent Canvas user logins. See the
 * <a href="https://canvas.instructure.com/doc/api/logins.html">Logins API</a>
 * documentation.
 */
@CanvasObject(postKey = "login")
@SuppressWarnings("serial")
public class Login extends BaseCanvasModel implements Serializable {
	private int id;
	private int userId;
	private int accountId;
	private String uniqueId;
	private String sisUserId;
	private String integrationId;
	private int integrationProviderId;
	private String integrationProviderType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@CanvasField(overrideObjectKey = "user", postKey = "user_id")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@CanvasField(postKey = "unique_id")
	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	@CanvasField(postKey = "sis_user_id")
	public String getSisUserId() {
		return sisUserId;
	}

	public void setSisUserId(String sisUserId) {
		this.sisUserId = sisUserId;
	}

	@CanvasField(postKey = "integration_id")
	public String getIntegrationId() {
		return integrationId;
	}

	public void setIntegrationId(String integrationId) {
		this.integrationId = integrationId;
	}

	@CanvasField(postKey = "integration_provider_id")
	public int getIntegrationProviderId() {
		return integrationProviderId;
	}

	public void setIntegrationProviderId(int integrationProviderId) {
		this.integrationProviderId = integrationProviderId;
	}

	public String getIntegrationProviderType() {
		return integrationProviderType;
	}

	public void setIntegrationProviderType(String integrationProviderType) {
		this.integrationProviderType = integrationProviderType;
	}
}
