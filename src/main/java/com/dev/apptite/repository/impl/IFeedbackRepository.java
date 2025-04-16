package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFeedbackRepository extends JpaRepository<Feedback,Long> {
}
