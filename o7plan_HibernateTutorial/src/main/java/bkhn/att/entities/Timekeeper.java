package bkhn.att.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

//Timekeeper - Máy chấm công, giờ ra vào của nhân viên

@Entity
@Table(name = "TIMEKEEPER")
public class Timekeeper {
	public static final char IN = 'I';
	public static final char OUT = 'O';

	private String timekeeperId;
	private Date dateTime;
	private Employee employee;
	private char inOut; // 'I' or 'O'

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "Timekeeper_Id", length = 36)
	public String getTimekeeperId() {
		return timekeeperId;
	}

	public void setTimekeeperId(String timekeeperId) {
		this.timekeeperId = timekeeperId;
	}

	@Column(name = "Date_Time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable = false)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "In_Out", nullable = false, length = 1)
	public char getInOut() {
		return inOut;
	}

	public void setInOut(char inOut) {
		this.inOut = inOut;
	}

	/*
	JPA Callbacks Method là gì?
	JPA callback method là các method lắng nghe các sự kiện như save, 
	update, remove các đối tượng trên database.
	
	Các annotation đánh dấu callback method:
	
	JPA cung cấp các annotation được sử dụng trong các class entity 
	(được đánh dấu @Entity) để lắng nghe các sự kiện save, update, 
	delete của chính các entity đó.
	
	@PrePersist: Thực thi trước khi entity được persist (được lưu 
	vào database) bởi method persist()
	@PostPersist: Thực thi sau khi entity được persist
	@PostLoad: Thực thi sau khi một entity được load vào persistence 
	           context hiện tại hoặc một entity được refreshed.
	@PreUpdate: Thực thi trước khi entity được update.
	@PostUpdate: Thực thi sau khi entity được update.
	@PreRemove: Thực thi trước khi entity bị xóa khỏi database bởi 
				method remove()
	@PostRemove: Thực thi sau khi entity bị xóa.
	
	Chạy file PersistEntityDemo để test
	 */
	@PrePersist
	public void prePersist() {
		System.out.println("Timekeeper pre persist!");
	}

	@PostPersist
	public void postPersist() {
		System.out.println("Timekeeper post persist!");
	}

	@PreUpdate
	public void preUpdate() {
		System.out.println("Timekeeper pre update!");
	}

	@PostUpdate
	public void postUpdate() {
		System.out.println("Timekeeper post update!");
	}

	@PreRemove
	public void preRemove() {
		System.out.println("Timekeeper pre remove!");
	}

	@PostRemove
	public void postRemove() {
		System.out.println("Timekeeper post remove!");
	}
}