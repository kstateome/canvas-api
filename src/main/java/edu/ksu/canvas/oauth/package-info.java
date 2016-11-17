/**
 * Functionality to handle OAuth tokens used for authenticating API calls.
 * <p>
 * This package deals with two different types of OAuth tokens used for
 * authentication:
 * <ul>
 * <li>Temporary access tokens with an associated long lived refresh token</li>
 * <li>Permanent manually generated tokens</li>
 * </ul>
 * <p>
 * The {@link edu.ksu.canvas.oauth.RefreshableOauthToken} class represents the
 * temporary access tokens with long lived refresh tokens. This type of token
 * should be used when executing API calls on behalf of a user, for example in
 * an LTI application. Sending the user through the OAuth flow will give you a
 * long lived <i>refresh token</i> which you can persist somewhere and then use
 * to get a short lived <i>access token</i> when performing API calls. When an
 * access token is generated, it comes with a time-to-live property. Currently
 * Canvas defaults to a one-hour TTL although this is subject to change without
 * notice. Once the access token expires a new one must be generated using the
 * refresh token.
 * <p>
 * Instances of RefreshableOauthToken are created by passing in the long lived
 * refresh token as well as the Canvas URL and client ID and client secret that
 * were used to generate the refresh token. It will then take care of creating
 * the temporary access token and refreshing it when it expires. You should
 * never need to know the temporary access token. Until Java's Project Jigsaw
 * makes it to the light of day, the method to get the access token must be
 * public but you should never call it. Dealing with the access token directly
 * could result in bad things happening.
 * <p>
 * The {@link edu.ksu.canvas.oauth.NonRefreshableOauthToken} class represents a
 * manually generated permanent access token. These are created via the Canvas
 * web UI on a user's account settings page. Typically these are only used when
 * an application is performing API calls from a back-end process that is not
 * making calls on behalf of a specific user. For example doing bulk SIS data
 * import processes or other things that need administrative level access.
 * <p>
 * 
 * @see <a href="https://canvas.instructure.com/doc/api/file.oauth.html"> Canvas
 *      OAuth documentation </a>
 */
package edu.ksu.canvas.oauth;