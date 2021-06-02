package edu.ksu.canvas.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * Class to represent Canvas user authentication logs.
 * See the <a href="https://canvas.instructure.com/doc/api/authentications_log.html">Authentications Log API</a> documentation.
 * NOTE: The fields returned from Canvas don't match the current documentation
 */
public class AuthenticationLog extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private List<AuthenticationEvent> events;
    private Object linked; //TODO: Refine this object type
    private Object links; //TODO: Refine this object type

    public enum EventType { login, logout }

    public class AuthenticationEvent {

        private Long id;
        private EventType eventType;
        private AuthenticationEventLinks links;

        private Instant createdAt;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public EventType getEventType() {
            return eventType;
        }

        public void setEventType(EventType eventType) {
            this.eventType = eventType;
        }

        public Instant getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
        }

        public AuthenticationEventLinks getLinks() {
            return links;
        }

        public void setLinks(AuthenticationEventLinks links) {
            this.links = links;
        }

        public class AuthenticationEventLinks {

            private Long user;
            private Long account;
            private Long login;

            public Long getUser() {
                return user;
            }

            public void setUser(Long user) {
                this.user = user;
            }

            public Long getAccount() {
                return account;
            }

            public void setAccount(Long account) {
                this.account = account;
            }

            public Long getLogin() {
                return login;
            }

            public void setLogin(Long login) {
                this.login = login;
            }
        }

    }

    public List<AuthenticationEvent> getEvents() {
        return events;
    }

    public void setEvents(List<AuthenticationEvent> events) {
        this.events = events;
    }

    public Object getLinked() {
        return linked;
    }

    public void setLinked(Object linked) {
        this.linked = linked;
    }

    public Object getLinks() {
        return links;
    }

    public void setLinks(Object links) {
        this.links = links;
    }
}
