package net.bobstudio.so.service;

import net.bobstudio.so.domain.Role;
import net.bobstudio.so.repository.RoleDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.Digests;
import org.springside.modules.utils.Encodes;

// Spring Bean的标识.
@Service
public class RoleService {

	private static Logger logger = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	private RoleDao roleDao;

	@Transactional(readOnly = true)
	public Iterable<Role> findAll() {
		return roleDao.findAll();
	}

	
	protected static String hashPassword(String password) {
		return Encodes.encodeBase64(Digests.sha1(password));
	}

	@Transactional
	public void saveRole(Role role) {
		roleDao.save(role);
		
	}
	
	@Transactional(readOnly = true)
	public Role findOne(Long id) {
		return roleDao.findOne(id);
	}
	
	@Transactional
	public void deleteRole(Long id){
		roleDao.delete(id);
	}

}
