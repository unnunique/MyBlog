/**
 * 
 */
package mblog.persist.dao.impl;

import mblog.persist.dao.UserDao;
import mblog.persist.entity.UserPO;
import mtons.modules.persist.impl.DaoImpl;
import mtons.modules.pojos.Paging;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Set;

/**
 * @author langhsu
 *
 */
public class UserDaoImpl extends DaoImpl<UserPO> implements UserDao {
	private static final long serialVersionUID = -3396151113305189145L;

	public UserDaoImpl() {
		super(UserPO.class);
	}
	
	@Override
	public UserPO getByUsername(String username) {
		return findUniqueBy("username", username);
	}

	@Override
	public UserPO getByEmail(String email) {
		List<UserPO> rets = findBy("email", email);

		if (rets != null && rets.size() > 0) {
			return rets.get(0);
		}
		return null;
	}

	@Override
	public List<UserPO> paging(Paging paging, String key) {
		PagingQuery<UserPO> q = pagingQuery(paging);
		if (StringUtils.isNotBlank(key)) {
			q.add(Restrictions.or(
				Restrictions.like("username", key, MatchMode.ANYWHERE),
				Restrictions.like("name", key, MatchMode.ANYWHERE)
			));
		}
		q.desc("id");
		return q.list();
	}

	@Override
	public List<UserPO> findByIds(Set<Long> ids) {
		return find(Restrictions.in("id", ids));
	}

	@Override
	public void identityPost(List<Long> userIds, boolean identity) {
		String substm = "+ 1";

		if (!identity) {
			substm = "- 1";
		}
		Query query = createSQLQuery("update mto_users set posts = posts " + substm + " where id in (:ids)");
		query.setParameterList("ids", userIds);
		query.executeUpdate();
	}

	@Override
	public void identityComment(List<Long> userIds, boolean identity) {
		String substm = "+ 1";

		if (!identity) {
			substm = "- 1";
		}
		Query query = createSQLQuery("update mto_users set comments = comments " + substm + " where id in (:ids)");
		query.setParameterList("ids", userIds);
		query.executeUpdate();
	}

	@Override
	public void identityFollow(List<Long> userIds, boolean identity) {
		String substm = "+ 1";

		if (!identity) {
			substm = "- 1";
		}
		Query query = createSQLQuery("update mto_users set follows = follows " + substm + " where id in (:ids)");
		query.setParameterList("ids", userIds);
		query.executeUpdate();
	}

	@Override
	public void identityFans(List<Long> userIds, boolean identity) {
		String substm = "+ 1";

		if (!identity) {
			substm = "- 1";
		}
		Query query = createSQLQuery("update mto_users set fans = fans " + substm + " where id in (:ids)");
		query.setParameterList("ids", userIds);
		query.executeUpdate();
	}

	@Override
	public void identityFavors(List<Long> userIds, boolean identity) {
		String substm = "+ 1";

		if (!identity) {
			substm = "- 1";
		}
		Query query = createSQLQuery("update mto_users set favors = favors " + substm + " where id in (:ids)");
		query.setParameterList("ids", userIds);
		query.executeUpdate();
	}

}
