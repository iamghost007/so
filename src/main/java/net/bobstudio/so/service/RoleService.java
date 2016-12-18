package net.bobstudio.so.service;

import net.bobstudio.so.domain.Role;
import net.bobstudio.so.dto.PageVo;
import net.bobstudio.so.repository.RoleDao;

import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;
import org.springside.modules.utils.Digests;
import org.springside.modules.utils.Encodes;

// Spring Bean的标识.
@Service
public class RoleService {

	//private static Logger logger = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	private RoleDao roleDao;

	@Transactional(readOnly = true)
	public Iterable<Role> findRoles() {
		return roleDao.findRoles();
	}
	
	@Transactional(readOnly = true)
	public Page<Role> findRoles(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = PageVo.buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Role> spec = buildSpecification(searchParams);

		return roleDao.findAll(spec, pageRequest); 
	}

	private Specification<Role> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("id", new SearchFilter("id", Operator.GT, 0L));
		Specification<Role> spec = DynamicSpecifications.bySearchFilter(filters.values(), Role.class);
		return spec;
	}

	@Transactional(readOnly = true)
	public Iterable<Role> findRoleTree() {
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
