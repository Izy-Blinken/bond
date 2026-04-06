/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bond.search;

import bond.dao.AnnouncementDAO;
import bond.dao.EventDAO;
import bond.dao.MemberDAO;
import bond.model.Announcement;
import bond.model.Event;
import bond.model.Member;

import java.util.*;

/**
 *
 * @author Erica
 */
public class GlobalSearchRegistry {
    
    public static final class SearchResult {
        public enum Type { EVENT, ANNOUNCEMENT, MEMBER }
        public final Type type;
        public final String title;
        public final String subtitle;
        public final Object source;

        public SearchResult(Type type, String title, String subtitle, Object source) {
            this.type = type; this.title = title;
            this.subtitle = subtitle; this.source = source;
        }
    }

    private static GlobalSearchRegistry instance;
    private final List<Event> events = new ArrayList<>();
    private final List<Announcement> announcements = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();

    private GlobalSearchRegistry() {
        reload();
    }

    public static GlobalSearchRegistry getInstance() {
        if (instance == null) instance = new GlobalSearchRegistry();
        return instance;
    }

    public void reload() {
        events.clear();
        announcements.clear();
        members.clear();
        try {
            int orgId = bond.util.SessionManager.getCurrentOrgId();
            if (orgId > 0) {
                events.addAll(new EventDAO().getAllEvents(orgId));
                announcements.addAll(new AnnouncementDAO().getAllAnnouncements(orgId));
                members.addAll(new MemberDAO().getAllMembers(orgId));
            }
        } catch (Exception ignored) {}
    }

    public List<SearchResult> search(String query) {
        String q = query.trim().toLowerCase();
        List<SearchResult> results = new ArrayList<>();
        if (q.isEmpty()) return results;

        for (Event e : events)
            if (e.getName().toLowerCase().contains(q))
                results.add(new SearchResult(SearchResult.Type.EVENT,
                    e.getName(), e.getDate(), e));

        for (Announcement a : announcements)
            if (a.getTitle().toLowerCase().contains(q))
                results.add(new SearchResult(SearchResult.Type.ANNOUNCEMENT,
                    a.getTitle(), a.getDate(), a));

        for (Member m : members)
            if (m.getName().toLowerCase().contains(q))
                results.add(new SearchResult(SearchResult.Type.MEMBER,
                    m.getName(), m.getRole(), m));

        return results;
    }
}
