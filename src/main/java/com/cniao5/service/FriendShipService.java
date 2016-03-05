package com.cniao5.service;

import com.cniao5.dao.FriendShipDao;
import com.cniao5.model.Friend;
import com.cniao5.model.FriendShip;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * FriendShipService层接口
 * Created by sony on 2015/12/14.
 */
@Service
public class FriendShipService {

    /**
     * 注入friendShipDao
     */
    @Resource
    private FriendShipDao friendShipDao;

    private static final String FIND_FRIENDS_SQL = "select m.id, m.name, m.email,m.registe_time as registeTime,m.headbig,m.headmid,m.headsmall,f.createtime "
            + " from tb_friend_ship f LEFT JOIN tb_member m ON f.friendid =m.ID "
            + " where f.memberid=?";

    /**
     * 添加好友关系
     *
     * @param friendShip
     */
    public void createFriendShip(FriendShip friendShip) {
        this.friendShipDao.save(friendShip);
    }

    /**
     * 通过会员id查找某个会员的好友
     *
     * @param memberid
     * @return 好友列表
     */
    public List<Friend> findMemberFriend(final Long memberid) {

        HibernateTemplate tmpl = friendShipDao.getHibernateTemplate();

        return tmpl.execute(new HibernateCallback<List<Friend>>() {
            @Override
            public List<Friend> doInHibernate(Session session)
                    throws HibernateException, SQLException {

                SQLQuery query = session.createSQLQuery(FIND_FRIENDS_SQL);
                //设置条件
                query.setLong(0, memberid);
                //设置标量查询类型
                query.addScalar("id", StandardBasicTypes.LONG);
                query.addScalar("name", StandardBasicTypes.STRING);
                query.addScalar("email", StandardBasicTypes.STRING);
                query.addScalar("registeTime", StandardBasicTypes.DATE);
                query.addScalar("headbig", StandardBasicTypes.STRING);
                query.addScalar("headmid", StandardBasicTypes.STRING);
                query.addScalar("headsmall", StandardBasicTypes.STRING);
                query.addScalar("createtime", StandardBasicTypes.DATE);

                //可以对原生SQL 查询使用ResultTransformer，这会返回不受Hibernate管理的实体。
                query.setResultTransformer(Transformers
                        .aliasToBean(Friend.class));

                List list = query.list();
                return list;
            }
        });

    }

    /**
     * 通过朋友id获取唯一的朋友对象
     * @param friendId
     * @return
     */
    public FriendShip getFriendByFriendId(long friendId) {
        return this.friendShipDao.findUniqueBy("friendid", friendId);
    }

    /**
     * 获取共同好友
     * @param memberid
     * @param friendid
     * @return
     */
    public List<FriendShip> findFriendShipByMemeberIdAndFriendId(long memberid,
                                                                 long friendid) {

        return this.friendShipDao.createCriteria(
                Restrictions.eq(FriendShip.MEMBER_ID, memberid),
                Restrictions.eq(FriendShip.FRIEND_ID, friendid)).list();
    }
}
